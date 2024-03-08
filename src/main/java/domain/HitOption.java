package domain;

public class HitOption {

    private final String value;

    public HitOption(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (!value.equalsIgnoreCase("y") && !value.equalsIgnoreCase("n")) {
            throw new IllegalArgumentException("[ERROR] y 혹은 n 만 입력 가능합니다.");
        }
    }

    public boolean doHit() {
        return value.equalsIgnoreCase("y");
    }
}
