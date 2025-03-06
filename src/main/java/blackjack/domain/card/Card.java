package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public final class Card {
    
    private final CardNumber number;
    private final CardShape shape;
    
    public Card(final int number, final CardShape shape) {
        validateNotNull(shape);
        this.number = CardNumber.from(number);
        this.shape = shape;
    }
    
    private void validateNotNull(final CardShape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("카드 모양은 null이 될 수 없습니다.");
        }
    }
    
    private Card(final CardNumber number, final CardShape shape) {
        this.number = number;
        this.shape = shape;
    }
    
    public static List<Card> createTrumpCards() {
        List<Card> cards = new ArrayList<>();
        for (CardNumber cardNumber : CardNumber.values()) {
            for (CardShape shape : CardShape.values()) {
                cards.add(new Card(cardNumber, shape));
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
