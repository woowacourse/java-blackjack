package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Player {

    protected final Cards cards;
    private final String name;

    Player(final List<Card> cards, final String name) {
        this.cards = new Cards(cards);
        this.name = name;
    }

    public int calculateFinalScore() {
        return cards.calculateFinalScore();
    }

    public void addCard(final Card card) {
        cards.addCard(card);
    }

    public boolean acceptableCard() {
        return false;
    }

    public List<Card> getCards() {
        return this.cards.getCards();
    }

    public String getName() {
        return this.name;
    }
}
