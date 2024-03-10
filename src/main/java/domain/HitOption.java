package domain;

public class HitOption {

    private static final String YES = "y";
    private static final String NO = "n";

    private final String value;

    public HitOption(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (!value.equalsIgnoreCase(YES) && !value.equalsIgnoreCase(NO)) {
            throw new IllegalArgumentException("[ERROR] " + YES + " 혹은 " + NO + " 만 입력 가능합니다.");
        }
    }

    public boolean isHit() {
        return value.equalsIgnoreCase(YES);
    }
}
