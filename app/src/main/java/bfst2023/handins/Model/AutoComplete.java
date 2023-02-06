package bfst2023.handins.Model;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutoComplete {

    private ArrayList<String> posibleSuggestion;

    public static void main(String[] args) throws IOException, URISyntaxException {
        AutoComplete auto = new AutoComplete();
        auto.number("pilemosevej 40");
        for (String string : auto.posibleSuggestion) {
            System.out.println(string);
        }

    }

    public AutoComplete() throws IOException, URISyntaxException {
        posibleSuggestion = new ArrayList<>();

        InputStream city = getClass().getClassLoader().getResourceAsStream("bfst2023/handins/assets/streetnames.txt");
        Scanner sc = new Scanner(new InputStreamReader(city, StandardCharsets.UTF_8));
        while (sc.hasNextLine()) {
            posibleSuggestion.add(sc.nextLine());
        }
    }

    public ArrayList<String> getPosibleSuggestion() {
        return posibleSuggestion;
    }

    public void number(String s) {
        ArrayList<String> tempPosib = new ArrayList<>();
        for (String string : posibleSuggestion) {
            Pattern pattern = Pattern.compile("([\\D\s.]+)(\\d)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(s);
            System.out.println(matcher.group(0));
            if (matcher.matches()) {
                if (matcher.group(1).toLowerCase().equals(string.toLowerCase())) {
                    InputStream postcode = getClass().getClassLoader()
                            .getResourceAsStream("bfst2023/handins/assets/postnumre.txt");
                    Scanner sc = new Scanner(new InputStreamReader(postcode, StandardCharsets.UTF_8));
                    while (sc.hasNextLine()) {
                        tempPosib.add(s.substring(0, 1) + s.substring(1) + " " + sc.nextLine());
                    }
                }
            }
        }
        posibleSuggestion = tempPosib;
    }
}