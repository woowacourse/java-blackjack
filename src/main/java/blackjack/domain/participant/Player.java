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

    public Result isWinner(int score) {
        if (!isBusted(calculateBestScore()) && (isBusted(score) || isCloserToBestScore(score))) {
            return Result.WIN;
        }
        if (!isBusted(calculateBestScore()) && calculateBestScore() == score) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    private boolean isBusted(int score) {
        return score > BUST_THRESHOLD;
    }

    private boolean isCloserToBestScore(int score) {
        return !isBusted(calculateBestScore()) && calculateBestScore() > score;
    }
}
