package blackjack.domain.participant;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int MAX_RECEIVABLE_SCORE = 17;

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public boolean isReceivable() {
        return calculateBestScore() < MAX_RECEIVABLE_SCORE;
    }

    public boolean hasHigherScore(Player player) {
        return calculateBestScore() > player.calculateBestScore() && !isBusted();
    }

    public boolean hasSameScore(Player player) {
        return calculateBestScore() == player.calculateBestScore();
    }
}
