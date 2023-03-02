package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeckGenerator {

    private CardDeckGenerator() {
        throw new IllegalStateException("생성할 수 없는 객체입니다.");
    }

    public static CardDeck create() {
        List<Card> cards = new ArrayList<>();

        for (Type type : Type.values()) {
            for (Value value : Value.values()) {
                cards.add(new Card(type, value));
            }
        }

        shuffleCards(cards);
        return new CardDeck(cards);
    }

    private static void shuffleCards(final List<Card> cards) {
        Collections.shuffle(cards);
    }
}
