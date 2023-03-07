package blackjack.domain;

public class Dealer extends User {

    public static final String DEALER_NAME = "딜러";
    private static final int FIRST_OPEN_CARD_COUNT = 1;
    private static final int DRAW_LIMIT_SCORE = 16;

    public Dealer(CardGroup initialGroup) {
        super(DEALER_NAME, initialGroup);
    }

    @Override
    public CardGroup getFirstOpenCardGroup() {
        return getCardGroups().getSubCardGroup(FIRST_OPEN_CARD_COUNT);
    }

    public WinningStatus comparePlayer(final Player player) {
        if (getScore().isBust()) {
            return compareByBust(player);
        }
        return compareByScore(player);
    }

    private WinningStatus compareByBust(final Player player) {
        if (player.getScore().isBust()) {
            return WinningStatus.TIE;
        }
        return WinningStatus.WIN;
    }

    private WinningStatus compareByScore(final Player player) {
        final Score playerScore = player.getScore();
        final Score dealerScore = getScore();
        if (playerScore.isBust() || dealerScore.isBigger(playerScore)) {
            return WinningStatus.LOSE;
        }
        if (playerScore.isEqual(dealerScore)) {
            return WinningStatus.TIE;
        }
        return WinningStatus.WIN;
    }

    public boolean isUnderDrawLimit() {
        return !getScore().isBigger(new Score(DRAW_LIMIT_SCORE));
    }
}
