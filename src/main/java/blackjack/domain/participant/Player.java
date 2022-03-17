package blackjack.domain.participant;

public class Player extends Participant {

    private static final int BEST_SCORE = 21;

    private Player(Name name) {
        super(name);
    }

    public static Player of(String name) {
        return new Player(new Name(name));
    }

    public boolean isCloserToBestScore(int score) {
        return calculateBestScore() <= BEST_SCORE && calculateBestScore() > score;
    }

    @Override
    public boolean isReceivable() {
        return calculateBestScore() <= BEST_SCORE;
    }
}
