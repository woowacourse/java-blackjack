package blackjack.domain;

import java.util.Arrays;

public enum Response {
    POSITIVE("y", true),
    NEGATIVE("n", false);

    private final String input;
    private final boolean hit;

    Response(String input, boolean hit) {
        this.input = input;
        this.hit = hit;
    }

    public static Response getResponse(String input) {
        return Arrays.stream(Response.values())
            .filter(response -> response.input.equalsIgnoreCase(input))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("불가능한 입력 입니다."));
    }

    public boolean getHitStatus() {
        return this.hit;
    }
}
