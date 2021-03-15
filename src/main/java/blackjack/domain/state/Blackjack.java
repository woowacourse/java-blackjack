package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public class Blackjack implements State {
    private final Cards cards;

    public Blackjack(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State takeCard(Card card) {
        cards.takeCard(card);
        return new Burst(cards);
    }

    @Override
    public boolean isBlackjack() {
        return true;
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
