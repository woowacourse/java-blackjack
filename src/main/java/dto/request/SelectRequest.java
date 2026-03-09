package dto.request;

import view.SelectFormat;

public record SelectRequest(boolean isPositive) {

    public static SelectRequest from(String input) {
        requireNonBlank(input);
        requireCorrectFormat(input);

        return new SelectRequest(choosePositive(input));
    }

    private static boolean choosePositive(String input) {
        return input.equals(SelectFormat.POSITIVE.sign());
    }

    private static void requireNonBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다.");
        }
    }

    private static void requireCorrectFormat(String input) {
        if (!SelectFormat.isCorrect(input)) {
            throw new IllegalArgumentException("y, n 이외의 값이 입력되었습니다.");
        }
    }
}
