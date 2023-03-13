package view;

import util.Constants;

public class InputValidator {

    private static final String INPUT_EMPTY_ERROR_MESSAGE = Constants.ERROR_PREFIX + "입력값이 존재하지 않습니다.";
    private static final String INPUT_DOES_NOT_Y_OR_N = Constants.ERROR_PREFIX + "추가 카드 요청 여부는 y 또는 n을 입력해 주세요.";

    public static void validateBlank(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(INPUT_EMPTY_ERROR_MESSAGE);
        }
    }

    public static void validateReceiveOrNot(String receiveOrNot) {
        if (!(receiveOrNot.equals("y") || receiveOrNot.equals("n"))) {
            throw new IllegalArgumentException(INPUT_DOES_NOT_Y_OR_N);
        }
    }
}
