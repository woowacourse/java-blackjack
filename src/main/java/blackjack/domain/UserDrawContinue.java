package blackjack.domain;


public class UserDrawContinue {
    private static final String YES = "y";
    private static final String NO = "n";

    private final boolean isContinue;

    public UserDrawContinue(String userInput) {
        validate(userInput);
        if (YES.equals(userInput)) {
            isContinue = true;
            return;
        }
        isContinue = false;
    }

    private void validate(String value) {
        if (!(YES.equals(value) || NO.equals(value))) {
            throw new IllegalArgumentException("입력은 y 또는 n만 가능합니다.");
        }
    }

    public boolean isContinue() {
        return isContinue;
    }
}
