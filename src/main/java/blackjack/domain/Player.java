package blackjack.domain;

public class Player extends Human {

    private static final String RECEIVE_SYMBOL = "y";
    private static final String NOT_RECEIVE_SYMBOL = "n";

    public Player(final String name) {
        super(name);
        validateEqualsDealerName(name);
    }

    public boolean answer(final String answer) {
        if (answer.equalsIgnoreCase(RECEIVE_SYMBOL)) {
            return true;
        }
        if (answer.equalsIgnoreCase(NOT_RECEIVE_SYMBOL)) {
            return false;
        }
        throw new IllegalArgumentException("y, n 중에서 입력해주세요.");
    }

    private void validateEqualsDealerName(final String name) {
        if (name.equals(Dealer.NAME)) {
            throw new IllegalArgumentException("딜러와 동일한 이름은 사용할 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isDrawable() {
        return playingCards.isUnderBlackjack();
    }
}
