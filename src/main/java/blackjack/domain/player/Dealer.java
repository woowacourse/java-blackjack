package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends AbstractPlayer {

    private static final String NAME = "딜러";
    private static final int DEALER_LIMIT_SCORE = 17;

    private Dealer(final String name, final Cards cards, final boolean turnState) {
        super(name, cards, turnState);
    }

    public static Dealer init(final List<Card> cards) {
        final Cards ownCards = new Cards(new ArrayList<>(cards));
        if (ownCards.calculateScore() >= DEALER_LIMIT_SCORE) {
            return new Dealer(NAME, ownCards, false);
        }
        return new Dealer(NAME, ownCards, true);
    }

    @Override
    public List<Card> initCards() {
        return List.copyOf(super.cards().subList(0, 1));
    }

    @Override
    public List<Card> cards() {
        validateEndTurn();
        return super.cards();
    }

    private void validateEndTurn() {
        if (canDraw()) {
            throw new IllegalStateException("딜러는 턴이 종료되지 않을 때 모든 카드를 반환할 수 없습니다.");
        }
    }

    @Override
    boolean isEnd() {
        return super.calculateScore() >= DEALER_LIMIT_SCORE;
    }
}
