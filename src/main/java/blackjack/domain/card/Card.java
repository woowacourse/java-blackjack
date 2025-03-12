package blackjack.domain.card;

import blackjack.util.GlobalValidator;

import java.util.ArrayList;
import java.util.List;

public final class Card {
    
    private final CardNumber number;
    private final CardShape shape;
    
    private Card(final CardNumber number, final CardShape shape) {
        GlobalValidator.validateNotNull(Card.class, number, shape);
        this.number = number;
        this.shape = shape;
    }
    
    public Card(final int number, final CardShape shape) {
        this(CardNumber.from(number), shape);
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
