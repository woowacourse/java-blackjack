package blackjack.domain.participant;

public class Player extends Participant {

    private Player(Name name) {
        super(name);
    }

    public static Player of(String name) {
        return new Player(new Name(name));
    }

    @Override
    public boolean isReceivable() {
        return calculateBestScore() <= BUST_THRESHOLD;
    }

    @Override
    public int calculateBestScore() {
        return cards.getBestPossible(cards.getLowestSum());
    }

    public boolean isWinner(int score) {
        return !isBusted(calculateBestScore()) && (isBusted(score) || isCloserToBestScore(score));
    }

    private boolean isBusted(int score) {
        return score > BUST_THRESHOLD;
    }

    private boolean isCloserToBestScore(int score) {
        return calculateBestScore() <= BUST_THRESHOLD && calculateBestScore() > score;
    }
}
