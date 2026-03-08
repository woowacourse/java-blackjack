package view.message;

import java.util.regex.Pattern;

public enum BinaryOptionMessage {

    YES("y"),
    NO("n");

    private static final String YES_NO_REGEX = "^[yn]$";

    private final String choice;

    BinaryOptionMessage(String choice) {
        this.choice = choice;
    }

    public static boolean isYes(String userInput) {
        if (!Pattern.matches(YES_NO_REGEX, userInput)) {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요.");
        }

        return userInput.equals(YES.getChoice());
    }

    public String getChoice() {
        return choice;
    }
}
