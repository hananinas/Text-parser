package bfst2023.handins.Model;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {
    private Pattern pattern;
    private String roadName;
    private int number;
    private int postalCode;
    private String city;

    public Parser() {
        this.pattern = Pattern.compile(
                "^(?<street>\\D+)(\\s)(?<number>\\d+)(\\s)(?<postalcode>\\d{4})(\\s)(?<city>\\D+)$",
                Pattern.CASE_INSENSITIVE);
    }

    public Address parse(String s) {

        if (s.isEmpty()) {
            throw new NullPointerException();
        }

        Matcher matcher = pattern.matcher(s);

        if (matcher.matches()) {
            roadName = matcher.group("street");
            number = Integer.parseInt(matcher.group("number"));
            postalCode = Integer.parseInt(matcher.group("postalcode"));
            city = matcher.group("city");
        } else {
            throw new NoMatchException();
        }

        return new Address(roadName, number, postalCode, city);
    }
}
