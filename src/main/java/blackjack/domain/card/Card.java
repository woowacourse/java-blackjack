package blackjack.domain.card;

public class Card {

    private Type type;
    private Score score;

    public Card(final Type type, final Score score) {
        this.type = type;
        this.score = score;
    }

    public Score getScore() {
        return score;
    }

    public Type getType() {
        return type;
    }
}
