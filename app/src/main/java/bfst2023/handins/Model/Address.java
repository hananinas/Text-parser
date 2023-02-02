package bfst2023.handins.Model;

import java.text.Format;
import java.util.regex.*;

public class Address {
    private String streetName;
    private int houseNumber;
    private int postalCode;
    private String cityName;

    public Address(String streetName, int houseNumber, int postalCode, String cityName) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.cityName = cityName;
    }

    public String getAddress() {
        return String.format("%s %s %s %s", streetName, houseNumber, postalCode, cityName);
    }

    public String getStreetName() {
        return streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void print() {
        System.out.printf("Streetname: %s \nHousenumber: %s \nPostalcode: %s \nCityname: %s", streetName,
                houseNumber, postalCode, cityName);
    }
}