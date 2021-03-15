package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public class Burst implements State {
    public static final String EXCEPTION_OVER_21 = "21을 초과하여 더이상 카드를 받을 수 없습니다.";
    private final Cards cards;

    public Burst(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State takeCard(Card card) {
        throw new IllegalArgumentException(EXCEPTION_OVER_21);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBurst() {
        return true;
    }

    @Override
    public Score calculateScore() {
        return cards.sumCardsForResult();
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public int size() {
        return cards.size();
    }

}
