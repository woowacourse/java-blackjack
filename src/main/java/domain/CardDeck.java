package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private static final int DECK_SIZE = 6;

    private List<Card> cards;

    private CardDeck(final List<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck generate() {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < DECK_SIZE; i++) {
            generateOneCardDeck(cards);
        }

        return new CardDeck(cards);
    }

    private static void generateOneCardDeck(final List<Card> cards) { //TODO 인덴트 줄이기
        for (CardShape cardShape : CardShape.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                cards.add(new Card(cardNumber, cardShape));
            }
        }
    }

    public int size() {
        return cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
