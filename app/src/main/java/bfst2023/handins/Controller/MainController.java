package bfst2023.handins.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import bfst2023.handins.Model.Address;
import bfst2023.handins.Model.AutoComplete;
import bfst2023.handins.Model.NoMatchException;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import javafx.scene.text.Text;

public class MainController implements Initializable {
    @FXML
    private Text city;
    @FXML
    private TextField inputField;
    @FXML
    private Text number;
    @FXML
    private Text postalcode;
    @FXML
    private Text street;
    @FXML

    private Text error;
    private Address address;
    private AutoComplete posibAutoComplete;
    private SuggestionProvider suggestionProvider;
    private int count;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        count = 0;


        try {
            posibAutoComplete = new AutoComplete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // data for the AutoCompletionTextFieldBinding textfield from controlsfx
        suggestionProvider = SuggestionProvider.create(posibAutoComplete.getPosibleSuggestion());
        new AutoCompletionTextFieldBinding<>(inputField, suggestionProvider);

        // If inputField is clicked and the count is over one reset the inputField
        inputField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (count == 1) {
                    System.out.println(count);
                    updateAutoFill();
                } else {
                    try {
                        resetAutoFill();
                        count = 0;
                        System.out.println(count);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    // When a action event is recorded use this to update the address
    public void upddateAddress(ActionEvent event) {

        try {

            // If error message is displayed reset it 
            if (error.getText() != null) {
                error.setText("");
            }

            // When a match is found set text
            address = Address.parse(inputField.getText());
            street.setText(address.street);
            postalcode.setText(address.postcode);
            city.setText(address.city);

            if (address.floor == null) {
                number.setText(address.house);
            } else if (address.side == null) {
                number.setText(address.house + address.side);
            } else {
                number.setText(address.house + " " + address.floor + " " + address.side);
            }
          
        } catch (NoMatchException e) {
            System.out.println(e.getMessage());
            error.setText(e.getMessage());
            posibAutoComplete.updateAutoFill(inputField.getText());
            count++;
            System.out.println(count);
        } catch (NullPointerException e) {
            error.setText("No input!");
        }

    }

    public void updateAutoFill() {
        Set<String> newSuggestions = new HashSet<>(posibAutoComplete.getPosibleSuggestion());
        suggestionProvider.clearSuggestions();
        suggestionProvider.addPossibleSuggestions(newSuggestions);
    }

    public void resetAutoFill() throws IOException {
        posibAutoComplete = new AutoComplete();
        suggestionProvider.clearSuggestions();
        suggestionProvider.addPossibleSuggestions(new HashSet<>(posibAutoComplete.getPosibleSuggestion()));
    }

    // Resets everthing 
    public void resetButton(ActionEvent event) throws IOException {
        resetAutoFill();
        inputField.clear();
        if (error.getText() != null) {
            error.setText("");
        }
        postalcode.setText("");
        street.setText("");
        number.setText("");
        city.setText("");
    }

}
