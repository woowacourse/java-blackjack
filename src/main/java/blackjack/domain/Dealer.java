package blackjack.domain;

public class Dealer extends Participant {

    private static final int CARD_TAKE_LIMIT = 17;

    public boolean isUnderTakeLimit() {
        return computeSumOfCards() < CARD_TAKE_LIMIT;
    }

    public PlayerResult judge(Player player) {
        int dealerSum = computeSumOfCards();
        if (isBust(dealerSum)) {
            return PlayerResult.WIN;
        }
        
        int playerSum = player.computeSumOfCards();
        if (playerSum == dealerSum) {
            return PlayerResult.PUSH;
        }
        if (playerSum > dealerSum) {
            return PlayerResult.WIN;
        }
        return PlayerResult.LOSE;
    }
}
