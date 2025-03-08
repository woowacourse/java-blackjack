package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public final class Card {
    
    private final CardNumber number;
    private final CardShape shape;
    
    private Card(final CardNumber number, final CardShape shape) {
        this.number = number;
        this.shape = shape;
    }
    
    public static List<Card> createTrumpCards() {
        List<Card> cards = new ArrayList<>();
        for (CardShape shape : CardShape.values()) {
            for (CardNumber number : CardNumber.values()) {
                cards.add(new Card(number, shape));
            }
        }
        return cards;
    }
    
    public CardNumber getNumber() {
        return number;
    }
    
    public List<Integer> getBlackjackValue() {
        return number.getBlackjackNumber();
    }
    
    public CardShape getShape() {
        return shape;
    }
}
