package blackjack.domain;

public class Select {

    private static final String CONTINUE_GAME_MARK = "y";
    private static final String END_GAME_MARK = "n";
    private final boolean isMoreCard;

    public Select(final String option) {
        this.isMoreCard = validateOption(option);
    }

    private boolean validateOption(final String option) {
        if (CONTINUE_GAME_MARK.equals(option)) {
            return true;
        }

        if (END_GAME_MARK.equals(option)) {
            return false;
        }
        throw new IllegalArgumentException("y 또는 n을 입력해야 합니다.");
    }

    public boolean getIsMoreCard() {
        return isMoreCard;
    }
}
