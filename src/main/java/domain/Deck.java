package domain;

import common.ErrorMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final int INIT_DECK_SIZE = 2;
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck createDeck() {
        List<Card> cards = new ArrayList<>();
        createAllCards(cards);
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private static void createAllCards(List<Card> cards) {
        for (CardShape shape : CardShape.values()) {
            for (CardContents content : CardContents.values()) {
                cards.add(new Card(shape, content));
            }
        }
    }

    public Deck giveInitialDeck() {
        return new Deck(
                this.drawCard(INIT_DECK_SIZE)
        );
    }

    public List<Card> drawCard(int count) {

        if (count > cards.size() || count < 1) {
            throw new IllegalArgumentException(ErrorMessage.DRAW_CARD_OUT_OF_RANGE.getMessage());
        }

        ArrayList<Card> selectedCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            selectedCards.add(cards.removeFirst());
        }
        return selectedCards;
    }
}
