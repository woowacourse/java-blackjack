package domain.model;

public class Bet {
    private final int betAmount;

    public Bet(int betAmount) {
        this.betAmount = betAmount;
    }

    public static Bet of(int betAmount) {
        return new Bet(betAmount);
    }

    public int getFinalMoney(PlayerStatus playerStatus) {
        if (playerStatus == PlayerStatus.LOSS) {
            return betAmount * -1;
        }
        if (playerStatus == PlayerStatus.WIN) {
            return betAmount;
        }
        if (playerStatus == PlayerStatus.BLACK_JACK) {
            return betAmount * 3 / 2;
        }
        // 무승부 경우
        return 0;
    }

    public int getBetAmount() {
        return betAmount;
    }
}
