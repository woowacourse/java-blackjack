package BlackJack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardFactory {

    private static List<Card> CARD_CACHE = new ArrayList<>();

    static {
        for (Shape shape : Shape.values()) {
            for(Number number : Number.values()){
                CARD_CACHE.add(new Card(shape, number));
            }
        }
    }

    public static List<Card> drawTwoCards(){
        Collections.shuffle(CARD_CACHE);
        List<Card> cards = CARD_CACHE.subList(0, 2);
        CARD_CACHE = new ArrayList<>(CARD_CACHE.subList(2, CARD_CACHE.size()));
        return cards;
    }

}
