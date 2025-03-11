package view;

public class InputValidator {

    private static final String NAMES_REGEX = "([a-zA-Z]+)(,[a-zA-Z]+)*";

    private InputValidator() {}

    public static void validateNameFormat(String input) {
        if (!input.matches(NAMES_REGEX)) {
            throw new IllegalArgumentException("[ERROR] (,)로 구분된 한글자 이상의 영어 이름을 입력해주세요.");
        }
    }

    public static void validateBetAmount(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자만 입력가능 합니다.");
        }
    }
}
