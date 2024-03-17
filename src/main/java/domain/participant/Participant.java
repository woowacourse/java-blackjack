package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.participant.name.Name;
import java.util.List;

public abstract class Participant {
    private final Name name;
    private final Cards cards;

    public Participant(Name name) {
        this.name = name;
        this.cards = new Cards();
    }

    public void receiveInitialCards(Card first, Card second) {
        receiveAdditionalCard(first);
        receiveAdditionalCard(second);
    }

    public void receiveAdditionalCard(Card card) {
        cards.addCard(card);
    }

    public int calculateScore() {
        return cards.sumAllCards();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public abstract boolean isNotFinished();

    public String getName() {
        return this.name.getValue();
    }

    public List<Card> getAllCards() {
        return cards.getCards();
    }
}
