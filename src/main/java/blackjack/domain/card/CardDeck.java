package blackjack.domain.card;

import blackjack.domain.participant.Participant;

import java.util.LinkedList;
import java.util.Queue;

public class CardDeck {
    private final Queue<Card> cards;

    public CardDeck() {
        cards = new LinkedList<>(Card.getInstance());
    }

    public void dealFirstCards(Participant participant) {
        participant.addCard(giveCard());
        participant.addCard(giveCard());
    }

    public void dealAdditionalCard(Participant participant) {
        participant.addCard(giveCard());
    }

    private Card giveCard() {
        return cards.poll();
    }

    public int getSize() {
        return cards.size();
    }
}
