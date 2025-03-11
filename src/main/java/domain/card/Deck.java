package domain.card;

import domain.card.cardsGenerator.CardsGenerator;
import domain.participant.Participant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class Deck {

    private final Deque<Card> cards;

    public Deck(CardsGenerator cardsGenerator) {
        cards = new ArrayDeque<>();
        cards.addAll(cardsGenerator.generate());
    }

    public Card pick() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 부족합니다.");
        }
        return cards.pop();
    }

    public void giveCardTo(Participant participant, int count) {
        for (int i = 0; i < count; i++) {
            participant.addCard(pick());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deck deck = (Deck) o;
        return Objects.equals(cards, deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
