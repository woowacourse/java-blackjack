package blackjack.domain.card;

/**
 * 하나의 카드에 대한 정보를 가지고 있는 자료구조입니다
 */
public class Card {

    private final Shape shape;
    private final Symbol symbol;

    public Card(final Shape shape, final Symbol symbol) {
        this.shape = shape;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Shape getShape() {
        return shape;
    }

    public int getScore() {
        return symbol.getScore();
    }

}
