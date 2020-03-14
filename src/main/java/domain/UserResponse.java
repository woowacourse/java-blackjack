package domain;

import java.util.Arrays;

public enum UserResponse {
    YES("y"),
    NO("n");

    private final String inputString;

    UserResponse(String inputString) {
        this.inputString = inputString;
    }

    public static UserResponse of(String inputString) {
        return Arrays.stream(values())
                .filter(userResponse -> userResponse.inputString.equals(inputString))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("값이 올바르지 않습니다."));
    }

    public boolean isYes() {
        return this == UserResponse.YES;
    }
}
