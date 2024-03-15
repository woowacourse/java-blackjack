package blackjack.domain.participants;

public class Player extends Gamer {
    public Player(Name name) {
        super(name);
    }

    public Result checkResult(int dealerScore, boolean isDealerBlackjack) {
        return checkResultWhenBust(dealerScore, isDealerBlackjack);
    }

    private Result checkResultWhenBust(int dealerScore, boolean isDealerBlackjack) {
        if (calculateScore() > BLACKJACK_SCORE) {
            return Result.LOSE;
        }
        if (dealerScore > BLACKJACK_SCORE) {
            return Result.WIN;
        }
        return checkResultWhenBlackjack(dealerScore, isDealerBlackjack);
    }

    private Result checkResultWhenBlackjack(int dealerScore, boolean isDealerBlackjack) {
        if (isBlackjack() && isDealerBlackjack) {
            return Result.TIE;
        }
        if (isBlackjack()) {
            return Result.BLACKJACK_WIN;
        }
        return checkResultWhenNormal(dealerScore);
    }

    private Result checkResultWhenNormal(int dealerScore) {
        if (calculateScore() < dealerScore) {
            return Result.LOSE;
        }
        if (calculateScore() > dealerScore) {
            return Result.WIN;
        }
        return Result.TIE;
    }

    public void betMoney(GamblingMoney gamblingMoney) {
        playerStatus.addMoney(gamblingMoney);
    }

    public void checkBettingMoney(float benefit) {
        playerStatus.calculateBettingMoney(benefit);
    }

    @Override
    public boolean isNotOver() {
        return playerStatus.calculateScore() < BLACKJACK_SCORE;
    }
}
