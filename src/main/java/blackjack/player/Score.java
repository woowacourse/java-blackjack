package blackjack.player;

public class Score {

    private int score;

    public Score(int score) {
        this.score = score;
    }

    public Score add(int score) {
        return add(new Score(score));
    }

    public Score add(Score other) {
        return new Score(this.score + other.score);
    }
}
