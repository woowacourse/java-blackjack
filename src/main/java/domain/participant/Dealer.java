package domain.participant;

public class Dealer extends Participant {
    private static final int MAXIMUM_ENABLE_TOTAL_SCORE = 16;

    public Dealer(final Hand hand) {
        super(hand);
    }

    public static Dealer init() {
        return new Dealer(Hand.init());
    }

    @Override
    public boolean isDrawable() {
        return hand.getTotalScore()
                .isEqualOrLess(MAXIMUM_ENABLE_TOTAL_SCORE);
    }

    public boolean isWin(final Player player) {
           return player.isBust()
                || (this.isBlackJack() && !player.isBlackJack())
                || (!this.isBust() && this.getTotalScore().isBigger(player.getTotalScore()));
    }
}
