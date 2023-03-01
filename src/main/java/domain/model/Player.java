package domain.model;

public class Player {

    private final Cards cards;
    private Score score;

    public Player(final Cards cards) {
        this.cards = cards;
        this.score = makeScore(cards);
    }

    private Score makeScore(final Cards cards) {
        return Score.of(cards);
    }

    public void addCard(final Card card) {
        cards.add(card);
        score = makeScore(cards);
    }

    public boolean isBust() {
        return score.isBust();
    }

    public Score getScore() {
        return score;
    }
}
