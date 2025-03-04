package blackjack.domain;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static blackjack.domain.CardShape.*;

public class Card {
    
    private static final EnumSet<CardShape> SHAPES = EnumSet.of(하트, 다이아몬드, 스페이드, 클로버);
    private static final int NUMBER_MIN_RANGE = 1;
    private static final int NUMBER_MAX_RANGE = 13;
    public static final List<Integer> ACE_NUMBERS = List.of(1, 11);
    public static final int CARD_MAX_VALUE = 10;
    public static final int ACE_NUMBER = 1;
    
    private final int number;
    private final CardShape shape;
    
    public Card(final int number, final CardShape shape) {
        validateNumber(number);
        validateShape(shape);
        this.number = number;
        this.shape = shape;
    }
    
    public static List<Card> createTrumpCards() {
        List<Card> cards = new ArrayList<>();
        for (int number = NUMBER_MIN_RANGE; number <= NUMBER_MAX_RANGE; number++) {
            for (CardShape shape : CardShape.values()) {
                cards.add(new Card(number, shape));
            }
        }
        return cards;
    }
    
    private void validateNumber(int number) {
        if (number < NUMBER_MIN_RANGE || number > NUMBER_MAX_RANGE) {
            throw new IllegalArgumentException("숫자는 1부터 13 사이여야 합니다.");
        }
    }
    
    private void validateShape(CardShape shape) {
        if (!SHAPES.contains(shape)) {
            throw new IllegalArgumentException("문양은 하트, 다이아몬드, 스페이드, 클로버 중 하나여야 합니다.");
        }
    }
    
    public int getNumber() {
        return number;
    }
    
    public List<Integer> getBlackjackNumber() {
        if (number == ACE_NUMBER) {
            return ACE_NUMBERS;
        }
        return List.of(Math.min(number, CARD_MAX_VALUE));
    }
    
    public CardShape getShape() {
        return shape;
    }
}
