package blackjack.domain.card;

public class CardDto {

    private final Denomination denomination;
    private final Shape shape;
    private final int score;

    public CardDto(Denomination denomination, Shape shape, int score) {
        this.denomination = denomination;
        this.shape = shape;
        this.score = score;
    }

    public static CardDto from(Card card) {
        return new CardDto(card.getDenomination(), card.getShape(), card.calculateScore());
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Shape getShape() {
        return shape;
    }

    public int getScore() {
        return score;
    }
}
