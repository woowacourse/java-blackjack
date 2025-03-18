package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public final class TrumpCards {
    
    private final static List<Card> trumpCards = initTrumpCards();
    
    private static List<Card> initTrumpCards() {
        List<Card> cards = new ArrayList<>();
        for (CardNumber number : CardNumber.values()) {
            for (CardShape shape : CardShape.values()) {
                cards.add(new Card(number, shape));
            }
        }
        return cards;
    }
    
    public static List<Card> createTrumpCards() {
        return trumpCards;
    }
}
