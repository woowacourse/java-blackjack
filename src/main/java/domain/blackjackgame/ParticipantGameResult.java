package domain.blackjackgame;

public enum ParticipantGameResult {

    WIN("승", 1.0),
    BLACKJACK("블랙잭", 1.5),
    LOSE("패", -1.0),
    DRAW("무승부", 0.0);
    private static final int BUST_STANDARD = 21;

    private final String status;
    private final double earnMoneyMultiple;


    ParticipantGameResult(String status, double earnMoneyMultiple) {
        this.status = status;
        this.earnMoneyMultiple = earnMoneyMultiple;
    }

    public static ParticipantGameResult of(int participantCardSum, int againstCardSum, boolean blackjack) {
        if (blackjack) {
            return ParticipantGameResult.BLACKJACK;
        }
        if (isParticipantDraw(againstCardSum, participantCardSum)) {
            return ParticipantGameResult.DRAW;
        }
        if (isParticipantWin(againstCardSum, participantCardSum)) {
            return ParticipantGameResult.WIN;
        }
        return ParticipantGameResult.LOSE;
    }

    private static boolean isParticipantWin(int dealerSum, int playerSum) {
        if (isBust(dealerSum) && isBust(playerSum)) {
            return false;
        }
        if (isBust(playerSum)) {
            return false;
        }
        if (isBust(dealerSum)) {
            return true;
        }
        return playerSum > dealerSum;
    }

    private static boolean isParticipantDraw(int dealerSum, int playerSum) {
        if (dealerSum == playerSum) {
            return true;
        }
        if (isBust(dealerSum) && isBust(playerSum)) {
            return true;
        }
        return false;
    }

    private static boolean isBust(int sum) {
        return sum > BUST_STANDARD;
    }

    public double calculateEarnMoney(int money) {
        return money + this.earnMoneyMultiple * money;
    }

    public String getStatus() {
        return status;
    }
}
