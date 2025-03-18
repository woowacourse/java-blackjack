package blackjack.domain.card;

import blackjack.util.GlobalValidator;

import java.util.List;

public final class Card {
    
    private final CardNumber number;
    private final CardShape shape;
    
    public Card(final CardNumber number, final CardShape shape) {
        GlobalValidator.validateNotNull(number, shape);
        this.number = number;
        this.shape = shape;
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
