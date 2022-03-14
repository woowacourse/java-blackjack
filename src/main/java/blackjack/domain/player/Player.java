package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Player {

    protected final Cards cards;
    private final String name;

    Player(final String name) {
        this.cards = new Cards();
        this.name = name;
    }

    public abstract boolean acceptableCard();

    public int calculateFinalScore() {
        return cards.calculateFinalScore();
    }

    public void addCard(final Card card) {
        cards.addCard(card);
    }

    public List<Card> getCards() {
        return this.cards.getCards();
    }

    public String getName() {
        return this.name;
    }

}
