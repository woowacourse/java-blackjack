package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final int PICK_CARD_INDEX = 0;

    private List<Card> cards;

    public Deck(List<Card> cards) {
        Collections.shuffle(cards);
        this.cards = new ArrayList<>(List.copyOf(cards));
    }

    public Card pickCard() {
        return cards.remove(PICK_CARD_INDEX);
    }

    public List<Card> pickCards(int count) {
        List<Card> pickCards = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            pickCards.add(cards.remove(PICK_CARD_INDEX));
        }
        return pickCards;
    }
}
