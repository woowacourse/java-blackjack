package domain;

public class Dealer extends Player {

    private static final int PICK_BOUNDARY = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new PlayerName(DEALER_NAME));
    }

    public boolean isHittable() {
        return getScore().getValue() <= PICK_BOUNDARY;
    }

    public Status getDealerStats(final Player player) {
        Score playerScore = player.getScore();
        Score dealerScore = getScore();
        if (player.isBustedPlayer() && this.isBustedPlayer()) {
            return Status.DRAW;
        }
        if (player.isBlackJack()) {
            return Status.LOSE;
        }
        return dealerScore.compareScore(playerScore);
    }
}
