package blackjack.domain;

import java.util.Arrays;

public enum Response {
    YES("y"),
    NO("n");

    private String lowerCase;

    Response(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    public static Response of(String inputValue) {
        String lowerCase = inputValue.toLowerCase();
        return Arrays.stream(values())
                .filter(response -> response.lowerCase.equals(lowerCase))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n을 입력해주세요."));
    }

    public static boolean isYes(String inputResponse) {
        String lowerCase = inputResponse.trim().toLowerCase();
        return lowerCase.equals(YES.lowerCase);
    }

    public static boolean isCorrect(String inputResponse) {
        String lowerCase = inputResponse.trim().toLowerCase();
        return Arrays.stream(values())
                .anyMatch(response -> response.lowerCase.equals(lowerCase));
    }
}
