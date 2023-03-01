import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SpecialCard {
    ACE("A", 1),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private final String value;

    private final int number;

    SpecialCard(String value, int number) {
        this.value = value;
        this.number = number;
    }

    public static boolean isSpecial(String value) {
        List<String> specialNames = Arrays.stream(values())
                .map(card -> card.value)
                .collect(Collectors.toUnmodifiableList());
        return specialNames.contains(value);
    }

    public static int convertNumber(String value) {
        return Arrays.stream(values())
                .filter(card -> card.value.equals(value))
                .map(card -> card.number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("특수 카드가 아닙니다."));
    }
}
