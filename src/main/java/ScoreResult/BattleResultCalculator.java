package ScoreResult;


import participant.Participant;

public class BattleResultCalculator {

    public BattleResult calculate(final Participant dealer, final Participant player) {
        if (hasBust(dealer, player)) {
            return determineBustResult(dealer, player);
        }
        return determineNormalResult(dealer, player);
    }

    private boolean hasBust(final Participant dealer, final Participant player) {
        return dealer.isBust() || player.isBust();
    }

    private BattleResult determineBustResult(final Participant dealer, final Participant player) {
        if ((player.isBust() && dealer.isBust()) || player.isBust()) {
            return BattleResult.LOSE;
        }
        return BattleResult.WIN;
    }

    private BattleResult determineNormalResult(final Participant dealer, final Participant player) {
        int dealerScore = dealer.getScore();
        int playerScore = player.getScore();
        if (playerScore > dealerScore) {
            return BattleResult.WIN;
        }
        if (playerScore < dealerScore) {
            return BattleResult.LOSE;
        }
        return BattleResult.DRAW;
    }

}

