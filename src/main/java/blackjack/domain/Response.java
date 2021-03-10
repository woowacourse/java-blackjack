package blackjack.domain;

import java.util.Arrays;

public enum Response {
    POSITIVE("y"),
    NEGATIVE("n");

    private final String input;

    Response(String input) {
        this.input = input;
    }

    public static Response getResponse(String input) {
        return Arrays.stream(Response.values())
            .filter(response -> response.input.equalsIgnoreCase(input))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("불가능한 입력 입니다."));
    }
}
