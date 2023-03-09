package domain.player;

import domain.blackjack.BlackjackRule;
import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public abstract class Participant {
    protected static final String DEALER_NAME = "딜러";

    protected final Cards cards;
    protected final Name name;

    protected Participant(Name name) {
        this.cards = new Cards();
        this.name = name;
    }

    public void receive(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public boolean isBusted() {
        return calculateScore() > BlackjackRule.BUST_LIMIT.getValue();
    }

    public int cardSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public String getName() {
        return name.getName();
    }

    protected boolean isBlackjack() {
        return cards.isBlackjack();
    }
}
