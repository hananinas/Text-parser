package bfst2023.handins.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.checkerframework.checker.units.qual.A;

import bfst2023.handins.Model.Address;
import bfst2023.handins.Model.NoMatchException;
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
    @FXML
    private Text error;

    private Address address;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    public void upddateAddress(ActionEvent event) {

        try {
            address = Address.parse(inputField.getText());
            System.out.println(address.city);
            street.setText(address.street);
            System.out.println(address.floor);

            if (address.floor == null) {
                number.setText(address.house);
            } else if(address.side == null) {
                number.setText(address.house + address.side);
            } else {
                number.setText(address.house + " " + address.floor + " " + address.side);
            }

            postalcode.setText(address.postcode);
            city.setText(address.city);
        } catch (NoMatchException e) {
            System.out.println(e.getMessage());
            error.setText(e.getMessage());
        } catch (NullPointerException e) {
            error.setText("No input!");
        }

    }

}
