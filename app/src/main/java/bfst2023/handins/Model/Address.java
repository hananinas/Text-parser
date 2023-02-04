package bfst2023.handins.Model;

import java.util.regex.Pattern;

public class Address {
    public final String street, house, floor, side, postcode, city;

    private Address(
            String _street, String _house, String _floor, String _side,
            String _postcode, String _city) {
        street = _street;
        house = _house;
        floor = _floor;
        side = _side;
        postcode = _postcode;
        city = _city;
    }

    public String toString() {
        return street + " " + house + ", " + floor + " " + side + "\n"
                + postcode + " " + city;
    }

    private final static String REGEX = "^(?<street>[\\D\\s.]+)\\s+(?<number>\\d+\\D?),\\s+((?<floor>st\\.|kl\\.|[\\d.]{0,2})\\s?(?<side>th|tv|mf|\\D\\d{1,2}))?(?<additionalCity>[^,]+)?(, )?(?<postcode>\\d{4})\\s*(?<city>\\D+)$";
    private final static Pattern PATTERN = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);

    public static Address parse(String input) {
        var builder = new Builder();
        var matcher = PATTERN.matcher(input);

        if (matcher.matches()) {
            builder.postcode(matcher.group("postcode"));
            builder.city(matcher.group("city"));
            builder.side(matcher.group("side"));
            builder.floor(matcher.group("floor"));
            builder.house(matcher.group("number"));
            builder.street(matcher.group("street"));
        } else if (input.isEmpty()) {
            throw new NullPointerException();
        } else {
            throw new NoMatchException();
        }

        return builder.build();

    }

    public static class Builder {
        private String street, house, floor, side, postcode, city;

        public Builder street(String _street) {
            street = _street;
            return this;
        }

        public Builder house(String _house) {
            house = _house;
            return this;
        }

        public Builder floor(String _floor) {
            floor = _floor;
            return this;
        }

        public Builder side(String _side) {
            side = _side;
            return this;
        }

        public Builder postcode(String _postcode) {
            postcode = _postcode;
            return this;
        }

        public Builder city(String _city) {
            city = _city;
            return this;
        }

        public Address build() {
            return new Address(street, house, floor, side, postcode, city);
        }

    }

}
