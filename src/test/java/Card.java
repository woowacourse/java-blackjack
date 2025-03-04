import java.util.List;

public class Card {
    private final List<String> numbers = List.of("2", "3", "4", "5", "6", "7", "8", "9", "10");
    private final List<String> specialNumbers = List.of("Q", "K", "J");

    private final String value;
    private final String type;

    public Card(String value, String type) {
        validate(value);
        this.value = value;
        this.type = type;
    }

    public int getNumber() {
        if (specialNumbers.contains(value)) {
            return 10;
        }
        return Integer.parseInt(value);
    }

    private void validate(String value) {
        if (!(numbers.contains(value) || specialNumbers.contains(value))) {
            throw new IllegalArgumentException("카드 숫자는 2 ~ 10 사이 숫자만 가능합니다.");
        }
    }
}
