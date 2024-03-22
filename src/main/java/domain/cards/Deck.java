package domain.cards;

import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final int FIRST_CARD = 0;

    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck createShuffledDeck() {
        List<Card> cards = new ArrayList<>();
        for (CardNumber cardNumber : CardNumber.values()) {
            addCardWithCardNumber(cardNumber, cards);
        }
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private static void addCardWithCardNumber(CardNumber cardNumber, List<Card> cards) {
        for (CardShape cardShape : CardShape.values()) {
            cards.add(Card.valueOf(cardNumber, cardShape));
        }
    }

    public Card pickOneCard() {
        return cards.remove(FIRST_CARD);
    }
}
