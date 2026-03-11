package model;

public record BetPrice(Integer value) {
    public BetPrice(String name) {
        this(parseInt(name));
        validate(value);
    }

    private static void validate(Integer value) {
        if(value < 0) {
            throw new IllegalArgumentException("");
        }
    }

    private static Integer parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("21억 이하의 유효한 숫자를 입력해주세요.");
        }
    }

}
