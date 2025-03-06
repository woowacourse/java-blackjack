package blackjack.domain.card;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static blackjack.domain.card.CardShape.*;

public final class Card {
    
    private static final EnumSet<CardShape> SHAPES = EnumSet.of(하트, 다이아몬드, 스페이드, 클로버);
    
    private final CardNumber number;
    private final CardShape shape;
    
    public Card(final int number, final CardShape shape) {
        validateShape(shape);
        this.number = CardNumber.from(number);
        this.shape = shape;
    }
    
    private Card(final CardNumber number, final CardShape shape) {
        validateShape(shape);
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
    
    private void validateShape(final CardShape shape) {
        if (!SHAPES.contains(shape)) {
            throw new IllegalArgumentException("문양은 하트, 다이아몬드, 스페이드, 클로버 중 하나여야 합니다.");
        }
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
