package bfst2023.handins.Controller;


import java.net.URL;
import java.util.ResourceBundle;

import bfst2023.handins.Model.Address;
import bfst2023.handins.Model.NoMatchException;
import bfst2023.handins.Model.Parser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
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

    private Parser parser;
    private Address address;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        parser = new Parser();
    }

    public void upddateAddress(ActionEvent event) {

        try {

            address = parser.parse(inputField.getText());
            street.setText(address.getStreetName());
            number.setText("" + address.getHouseNumber());
            postalcode.setText("" + address.getPostalCode());
            city.setText(address.getCityName());

        } catch (NoMatchException e) {

            System.out.println(e.getMessage());
            inputField.setText(e.getMessage());

        } catch (NullPointerException e) {

            inputField.setText("no input!");

        }

    }

}


