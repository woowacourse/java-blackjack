package domain.participant;

public class Player extends Participant {
    private static final int MAXIMUM_ENABLE_TOTAL_SCORE = 20;

    private final PlayerName playerName;

    public Player(final PlayerName playerName, final Hand hand) {
        super(hand);
        this.playerName = playerName;
    }

    public static Player of(final PlayerName playerName) {
        return new Player(playerName, Hand.init());
    }

    @Override
    public boolean isDrawable() {
        return hand.getTotalScore()
                .isEqualOrLess(MAXIMUM_ENABLE_TOTAL_SCORE);
    }

    public boolean isWin(final Dealer dealer) {
        return (!this.isBust() && dealer.isBust())
                || (this.isBlackJack() && !dealer.isBlackJack())
                || this.getTotalScore().isBigger(dealer.getTotalScore());
    }

    public PlayerName getPlayerName() {
        return playerName;
    }
}
