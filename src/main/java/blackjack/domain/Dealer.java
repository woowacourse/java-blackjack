package blackjack.domain;

public class Dealer extends Participant {

    private static final int CARD_TAKE_LIMIT = 17;

    public boolean isUnderTakeLimit() {
        return computeSumOfCards() < CARD_TAKE_LIMIT;
    }

    public ParticipantResult judge(Player player) {
        int dealerSum = computeSumOfCards();
        if (isBust(dealerSum)) {
            return ParticipantResult.WIN;
        }

        int playerSum = player.computeSumOfCards();
        if (playerSum == dealerSum) {
            return ParticipantResult.PUSH;
        }
        if (playerSum > dealerSum) {
            return ParticipantResult.WIN;
        }
        return ParticipantResult.LOSE;
    }

    public boolean isAvailable() {
        int sum = computeSumOfCards();
        return sum < CARD_TAKE_LIMIT;
    }
}
