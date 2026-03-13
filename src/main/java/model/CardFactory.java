package model;

import dto.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {

    private CardFactory() {
    }

    public static List<Card> createShuffledCards() {
        List<Card> cards = createCards();
        Collections.shuffle(cards);
        return List.copyOf(cards);
    }

    private static List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            for(CardNumber cardNumber : CardNumber.values()) {
                cards.add(new Card(shape,cardNumber));
            }
        }
        return cards;
    }


}
