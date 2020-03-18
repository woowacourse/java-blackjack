package blackjack.domain;

import java.util.Arrays;

public enum Response {
    YES("y"),
    NO("n");

    private static final String RESPONSE_NOT_MATCH_EXCEPTION_MESSAGE = "y 또는 n을 입력해주세요.";
    private String lowerCase;

    Response(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    public static boolean isYes(String inputValue) {
        return of(inputValue) == YES;
    }

    private static Response of(String inputValue) {
        String lowerCase = inputValue.toLowerCase();
        return Arrays.stream(values())
                .filter(response -> response.lowerCase.equals(lowerCase))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(RESPONSE_NOT_MATCH_EXCEPTION_MESSAGE));
    }
}
