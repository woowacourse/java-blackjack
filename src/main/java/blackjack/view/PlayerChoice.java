package blackjack.view;

public enum PlayerChoice {
    HIT("y"),
    STAND("n");

    private final String message;

    PlayerChoice(final String message) {
        this.message = message;
    }

    static boolean isDrawable(final String choice) {
        if (HIT.message.equals(choice)) {
            return true;
        }
        if (STAND.message.equals(choice)) {
            return false;
        }

        final String errorMessage = String.format("%s 또는 %s 만 입력할 수 있습니다.", HIT.message, STAND.message);
        throw new IllegalArgumentException(errorMessage);
    }

    public String getMessage() {
        return message;
    }
}
