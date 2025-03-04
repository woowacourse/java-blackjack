import java.util.List;

public class Card {

    private static final List<String> numbers = List.of("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Q", "K",
            "J");

    private final String value;
    private final String type;

    public Card(String value, String type) {
        validate(value);
        this.value = value;
        this.type = type;
    }

    private void validate(String value) {
        if (!numbers.contains(value)) {
            throw new IllegalArgumentException("카드 숫자는 2 ~ 10 사이 숫자만 가능합니다.");
        }
    }
}
