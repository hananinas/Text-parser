package bfst2023.handins.Model;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutoComplete {

    private ArrayList<String> possibleSuggestion, possibleSuggestionForPostCodes, possibleSuggestionForCityNames;

    private Pattern pattern;
    private Matcher matcher;

    public static void main(String[] args) throws IOException {
        AutoComplete auto = new AutoComplete();
        auto.updateAutoFill("Lilledal 30");
        for (String string : auto.possibleSuggestion) {
            System.out.println(string);
        }

    }

    public AutoComplete() throws IOException {
        possibleSuggestion = new ArrayList<>();
        possibleSuggestionForPostCodes = new ArrayList<>();
        possibleSuggestionForCityNames = new ArrayList<>();

        // Get the data from the txt file that contians all streetnames
        InputStream street = getClass().getClassLoader().getResourceAsStream("bfst2023/handins/assets/streetnames.txt");
        Scanner sc = new Scanner(new InputStreamReader(street, StandardCharsets.UTF_8));
        while (sc.hasNextLine()) {
            possibleSuggestion.add(sc.nextLine());
        }
        // Get the data from the txt file that contians all postnumre
        InputStream postcodes = getClass().getClassLoader()
                .getResourceAsStream("bfst2023/handins/assets/postnumre.txt");
        Scanner sc_post = new Scanner(new InputStreamReader(postcodes, StandardCharsets.UTF_8));
        while (sc_post.hasNextLine()) {
            possibleSuggestionForPostCodes.add(sc_post.nextLine());
        }
        // Get the data from the txt file that contians all postnumre
        InputStream city = getClass().getClassLoader().getResourceAsStream("bfst2023/handins/assets/citynames.txt");
        Scanner sc_city = new Scanner(new InputStreamReader(city, StandardCharsets.UTF_8));
        while (sc_city.hasNextLine()) {
            possibleSuggestionForCityNames.add(sc_city.nextLine());
        }

    }

    public ArrayList<String> getPosibleSuggestion() {
        return possibleSuggestion;
    }

    public void updateAutoFill(String s) {

        // initializ a temp ArrayList with possibilities
        ArrayList<String> tempPosib = new ArrayList<>();

        // check if the string is a street
        pattern = Pattern.compile("^(?<street>[\\D\\s.]+)(.+)$");
        matcher = pattern.matcher(s);
        if (matcher.matches()) {
            // loop trough the current List of possibleSuggestion
            for (String string : possibleSuggestion) {
                // Check if it is in the list of all possibleSuggestion
                if (matcher.group("street").trim().toLowerCase().equals(string.toLowerCase())) {
                    for (String postcode : possibleSuggestionForPostCodes) {
                        tempPosib.add(s.substring(0, 1).toUpperCase() + s.substring(1) + ", " + postcode);
                    }
                }
            }
            // make the temp list the new currentlist
            possibleSuggestion = tempPosib;
        }
    }
}