package view;

import java.util.Arrays;

public enum Response {
    YES("y"),
    NO("n");

    private final String answer;

    Response(String answer) {
        this.answer = answer;
    }

    public static Response findAnswer(String rawInput) {
        return Arrays.stream(Response.values())
                .filter(response -> response.answer.equals(rawInput))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("y, n을 입력해주세요"));
    }
}
