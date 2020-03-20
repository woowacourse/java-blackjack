package blackjack.domain.card;

import blackjack.domain.participant.Participant;

import java.util.LinkedList;
import java.util.Queue;

public class CardDeck {
    public static final int NUMBER_OF_FIRST_CARDS = 2;
    private final Queue<Card> cards;

    public CardDeck() {
        cards = new LinkedList<>(CardFactory.create());
    }

    public void dealFirstCards(Participant participant) {
        for (int i = 0; i < NUMBER_OF_FIRST_CARDS; i++) {
            participant.addCard(giveCard());
        }
    }

    public void dealAdditionalCard(Participant participant) {
        participant.addCard(giveCard());
    }

    private Card giveCard() {
        return cards.poll();
    }

    public int size() {
        return cards.size();
    }
}
