package blackjack.domain.card;

import blackjack.util.GlobalValidator;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.List;

public final class Card {
    
    private final static Table<CardNumber, CardShape, Card> trumpCards = initTrumpCards();
    
    private final CardNumber number;
    private final CardShape shape;
    
    private Card(final CardNumber number, final CardShape shape) {
        GlobalValidator.validateNotNull(number, shape);
        this.number = number;
        this.shape = shape;
    }
    
    private static Table<CardNumber, CardShape, Card> initTrumpCards() {
        Table<CardNumber, CardShape, Card> cards = HashBasedTable.create();
        for (CardNumber number : CardNumber.values()) {
            for (CardShape shape : CardShape.values()) {
                cards.put(number, shape, new Card(number, shape));
            }
        }
        return cards;
    }
    
    public static Table<CardNumber, CardShape, Card> createTrumpCards() {
        return trumpCards;
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
