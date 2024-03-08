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
            for (CardNumber cardNumber : CardNumber.values()) {
                cards.add(new Card(cardNumber, cardShape));
            }
        }
        Collections.shuffle(cards);
        return cards;
    }
}
