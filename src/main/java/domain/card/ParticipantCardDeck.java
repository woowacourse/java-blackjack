package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParticipantCardDeck {
    private final List<Card> cards;

    private ParticipantCardDeck(final List<Card> cards) {
        this.cards = cards;
    }

    public ParticipantCardDeck(final ParticipantCardDeck participantCardDeck) {
        this.cards = new ArrayList<>(participantCardDeck.cards);
    }

    public static ParticipantCardDeck generateEmptySet() {
        return new ParticipantCardDeck(new ArrayList<>());
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParticipantCardDeck participantCardDeck = (ParticipantCardDeck) o;
        return Objects.equals(cards, participantCardDeck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
