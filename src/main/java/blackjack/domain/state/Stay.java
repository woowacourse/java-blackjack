package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public class Stay implements State {
    public static final String EXCEPTION_STOP_TAKING_CARD = "그만받기로 했어요! 받을 수 없습니다.";
    private final Cards cards;

    public Stay(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State takeCard(Card card) {
        throw new IllegalArgumentException(EXCEPTION_STOP_TAKING_CARD);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBurst() {
        return false;
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
