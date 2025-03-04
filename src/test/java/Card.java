import java.util.List;

public class Card {
    private final List<String> specialNumbers = List.of("Q", "K", "J");

    private final String value;
    private final String type;

    public Card(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public int getNumber() {
        if (specialNumbers.contains(value)) {
            return 10;
        }
        return Integer.parseInt(value);
    }
}
