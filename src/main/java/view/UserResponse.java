package view;

import java.util.Arrays;

public enum UserResponse {
    HIT("y", true),
    STAND("n", false);

    private final String userInput;
    private final boolean isHit;

    UserResponse(String userInput, boolean isHit) {
        this.userInput = userInput;
        this.isHit = isHit;
    }

    public static UserResponse getUserResponse(String rawUserResponse) {
        return Arrays.stream(UserResponse.values())
                .filter(userResponse -> userResponse.getUserInput().equals(rawUserResponse))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("응답은 반드시 y 혹은 n만 가능합니다."));
    }

    public String getUserInput() {
        return userInput;
    }

    public boolean isHit() {
        return isHit;
    }
}
