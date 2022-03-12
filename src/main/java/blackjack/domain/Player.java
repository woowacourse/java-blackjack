package blackjack.domain;

public class Player extends Human {

    public Player(final String name) {
        super(name);
        validateEqualsDealerName(name);
    }

    public boolean answer(final String answer) {
        return Answer.isDraw(answer);
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
