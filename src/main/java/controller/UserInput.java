package controller;

import exception.IllegalBlackjackInputException;
import java.util.Arrays;

public enum UserInput {
    YES("y"),
    NO("n");

    private final String input;

    UserInput(final String input) {
        this.input = input;
    }

    public static UserInput from(final String input) {
        return Arrays.stream(values())
                .filter(value -> value.input.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalBlackjackInputException("y나 n 이외의 입력입니다. 다시 입력해주세요."));
    }
}
