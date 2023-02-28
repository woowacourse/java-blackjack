package blackjack.domain.card;

public class Card {

    private final int number;
    private final String shape;

    public Card(final int number, final String shape) {
        validateNumberRange(number);
        validateShape(shape);
        this.number = number;
        this.shape = shape;
    }

    private void validateShape(final String shape) {
        if (!("스페이드".equals(shape) || "하트".equals(shape) || "클로버".equals(shape) || "다이아몬드".equals(shape))) {
            throw new IllegalArgumentException("카드 모양은 스페이드, 다이아몬드, 하트, 클로버 중 하나여야 합니다.");
        }
    }

    private void validateNumberRange(final int number) {
        if (number < 1 || number > 10) {
            throw new IllegalArgumentException("카드의 숫자는 1이상 10이하여야 합니다.");
        }
    }

    public int getNumber() {
        return number;
    }

    public String getShape() {
        return shape;
    }
}
