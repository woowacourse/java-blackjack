public class Card {

    private final String value;
    private final String type;

    public Card(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public int getNumber() {
        if (value.equals("Q") || value.equals("K") || value.equals("J")) {
            return 10;
        }
        return 2;
    }
}
