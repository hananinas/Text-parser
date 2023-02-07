package bfst2023.handins.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import bfst2023.handins.Model.Address;
import bfst2023.handins.Model.AutoComplete;
import bfst2023.handins.Model.NoMatchException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

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
    private AutoCompletionBinding initText;
    private AutoCompletionBinding<String> updatedAutoText;

    private Address address;
    private AutoComplete posibAutoComplete;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            posibAutoComplete = new AutoComplete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateAutoFill();
    }

    public void upddateAddress(ActionEvent event) {

        try {
            address = Address.parse(inputField.getText());
            System.out.println(address.city);
            street.setText(address.street);
            System.out.println(address.floor);

            if (address.floor == null) {
                number.setText(address.house);
            } else if (address.side == null) {
                number.setText(address.house + address.side);
            } else {
                number.setText(address.house + " " + address.floor + " " + address.side);
            }
            postalcode.setText(address.postcode);
            city.setText(address.city);
        } catch (NoMatchException e) {
            System.out.println(e.getMessage());
            posibAutoComplete.number(inputField.getText());
            updateAutoFill();
        } catch (NullPointerException e) {
            error.setText("No input!");
        }

    }

    public void updateAutoFill() {
        TextFields.bindAutoCompletion(inputField, posibAutoComplete.getPosibleSuggestion());
    }

}
