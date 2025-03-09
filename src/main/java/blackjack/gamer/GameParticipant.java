package blackjack.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class GameParticipant {

    private final Cards cards;

    protected GameParticipant(Cards cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateSumOfCards() {
        return cards.calculateSum();
    }

    public abstract boolean shouldHit();

    public Cards getCards() {
        return cards;
    }
}
