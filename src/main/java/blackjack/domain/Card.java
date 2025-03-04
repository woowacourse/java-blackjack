package blackjack.domain;

import java.util.EnumSet;

import static blackjack.domain.CardShape.*;

public class Card {
    
    private static final EnumSet<CardShape> SHAPES = EnumSet.of(하트, 다이아몬드, 스페이드, 클로버);
    private static final int NUMBER_MIN_RANGE = 1;
    private static final int NUMBER_MAX_RANGE = 13;
    
    private final int number;
    private final CardShape shape;
    
    public Card(final int number, final CardShape shape) {
        validateNumber(number);
        validateShape(shape);
        this.number = number;
        this.shape = shape;
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
}
