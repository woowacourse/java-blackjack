package blackjack.domain.participant;

public class Bet {

    private static final String MINIMUM_BET_EXCEPTION_MESSAGE = "최소 베팅금액은 %d원 입니다.";
    private static final int MINIMUM_BET = 1000;
    private static final int DRAW_MONEY = 0;
    private static final double BLACKJACK_DIVIDEND = 1.5;

    private final int bet;

    public Bet(int bet) {
        validateBet(bet);
        this.bet = bet;
    }

    private void validateBet(int bet) {
        if (bet < MINIMUM_BET) {
            throw new IllegalArgumentException(String.format(MINIMUM_BET_EXCEPTION_MESSAGE, MINIMUM_BET));
        }
    }

    public int getLosingPrize() {
        return -bet;
    }

    public int getDrawPrize() {
        return DRAW_MONEY;
    }

    public int getWinningPrize() {
        return bet;
    }

    public int getBlackJackPrize() {
        return (int) (BLACKJACK_DIVIDEND * bet);
    }
}
