package blackjack.domain;

public class Dealer extends Participant {

    private static final int CARD_TAKE_LIMIT = 17;

    public boolean isUnderTakeLimit() {
        return computeSumOfCards() < CARD_TAKE_LIMIT;
    }

    public WinResult judge(Player player) {
        int dealerSum = computeSumOfCards();
        if (isBust(dealerSum)) {
            return WinResult.WIN;
        }

        int playerSum = player.computeSumOfCards();
        if (playerSum == dealerSum) {
            return WinResult.PUSH;
        }
        if (playerSum > dealerSum) {
            return WinResult.WIN;
        }
        return WinResult.LOSE;
    }

    public boolean isAvailable() {
        int sum = computeSumOfCards();
        return sum < CARD_TAKE_LIMIT;
    }
}
