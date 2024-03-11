package domain.cards;

import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardsGenerator {

    public static List<Card> generateRandomCards() {
        List<Card> cards = new ArrayList<>();
        for (CardShape cardShape : CardShape.values()) {
            addCardWithCardNumber(cardShape, cards);
        }
        Collections.shuffle(cards);
        return cards;
    }

    private static void addCardWithCardNumber(CardShape cardShape, List<Card> cards) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardNumber, cardShape));
        }
    }
}
