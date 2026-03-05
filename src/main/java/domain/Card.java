package domain;

public class Card {
    private final String value;
    private final String pattern;

    public Card(String value, String pattern) {
        this.value = value;
        this.pattern = pattern;
    }

    public int getNumber() {
        if (value.equals("K") || value.equals("Q") || value.equals("J")) {
            return 10;
        }
        if (value.equals("A")) {
            return 1;
        }
        return Integer.parseInt(value);
    }
}
