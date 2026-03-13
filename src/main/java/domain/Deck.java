package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final ParticipantCards participantCards;

    public Deck() {
        this.participantCards = createDeck();
    }

    public Card drawCard() {
        validateDeckSize();
        Card card = participantCards.removeFirst();
        return card;
    }

    private void validateDeckSize() {
        if (participantCards.getSize() == 0) {
            throw new IllegalArgumentException("덱에 카드가 없습니다.");
        }
    }

    private ParticipantCards createDeck() {
        List<Card> cards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            for (Number number : Number.values()) {
                cards.add(new Card(shape, number));
            }
        }
        Collections.shuffle(cards);
        return new ParticipantCards(cards);
    }
}
