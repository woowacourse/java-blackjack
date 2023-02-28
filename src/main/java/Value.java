import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Value {
    ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"),
    SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
    JACK("J"), QUEEN("Q"), KING("K");

    private final String value;

    Value(final String value) {
        this.value = value;
    }

    public static Value of(final String value) {
        return Arrays.stream(Value.values())
                .filter(element -> element.value.equals(value))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
