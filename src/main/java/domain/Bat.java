package domain;

public record Bat(int amount) {
    public static final int MINIMUM_AMOUNT_UNIT = 10_000;

    public Bat {
        validate(amount);
    }

    private void validate(int amount) {
        int remainder = amount % MINIMUM_AMOUNT_UNIT;
        if (remainder != 0) {
            throw new IllegalArgumentException("베팅 금액의 최소 단위는 10,000원입니다.");
        }
    }
}
