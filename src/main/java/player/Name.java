package player;

public class Name {
    public static final int MIN_NAME_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 5;
    private final String value;

    public Name(String value) {
        String valueWithoutBlank = removeBlank(value);
        validateNameLength(valueWithoutBlank);
        this.value = valueWithoutBlank;
    }

    private static String removeBlank(String value) {
        return value.replace(" ", "");
    }

    private static void validateNameLength(String value) {
        if (value.length() < MIN_NAME_LENGTH || value.length() > MAX_NAME_LENGTH) {
            String message = String.format("이름은 %d글자 이상 %d글자 이하만 가능합니다.", MIN_NAME_LENGTH, MAX_NAME_LENGTH);
            throw new IllegalArgumentException(message);
        }
    }

    public String getValue() {
        return value;
    }
}
