package view;

public enum Answer {
    YES("y"),
    NO("n");

    private final String response;

    Answer(String response) {
        this.response = response;
    }

    public static boolean isYes(final String userInput) {
        if (userInput.equals(YES.response)) {
            return true;
        }

        if (userInput.equals(NO.response)) {
            return false;
        }

        throw new IllegalArgumentException(Error.formatMessage("y 또는 n으로 입력해주세요."));
    }
}
