package domain.card;

import domain.card.cardsGenerator.CardsGenerator;
import domain.participant.Participant;
import java.util.Stack;

public class Deck {

    private final Stack<Card> cards;

    public Deck(CardsGenerator cardsGenerator) {
        cards = new Stack<>();
        cards.addAll(cardsGenerator.generate());
    }

    public Card pick() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 부족합니다.");
        }
        return cards.pop();
    }

    public void giveCardTo(Participant participant) {
        participant.addCard(pick());
    }
}
