package domain;

import java.util.Arrays;

public enum DrawMore {
    YES("y"), NO("n");

    private final String answer;

    DrawMore(String answer) {
        this.answer = answer;
    }

    public static boolean isCorrectAnswer(String input) {
        return Arrays.stream(values())
                .anyMatch(value -> value.answer.equals(input));
    }

    public static boolean isYes(String input){
        return YES.answer.equals(input);
    }
}
