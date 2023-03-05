package blackjack.response;

import blackjack.domain.Card;
import blackjack.domain.Shape;
import blackjack.domain.Symbol;

public class CardConvertStrategyImpl implements CardConvertStrategy {

    @Override
    public String convert(Card card) {
        return convert(card.getShape()) + convert(card.getSymbol());
    }

    private String convert(Shape shape) {
        switch (shape) {
            case SPADE:
                return "스페이드";
            case DIAMOND:
                return "다이아몬드";
            case HEART:
                return "하트";
            case CLOVER:
                return "클로버";
            default:
                throw new IllegalArgumentException("존재하지 않는 모양입니다");
        }
    }


    private String convert(Symbol symbol) {
        switch (symbol) {
            case ACE:
                return "A";
            case TWO:
                return "2";
            case THREE:
                return "3";
            case FOUR:
                return "4";
            case FIVE:
                return "5";
            case SIX:
                return "6";
            case SEVEN:
                return "7";
            case EIGHT:
                return "8";
            case NINE:
                return "9";
            case TEN:
                return "10";
            case JACK:
                return "J";
            case QUEEN:
                return "Q";
            case KING:
                return "K";
            default:
                throw new IllegalArgumentException("존재하지 않는 숫자 입니다");
        }
    }


}
