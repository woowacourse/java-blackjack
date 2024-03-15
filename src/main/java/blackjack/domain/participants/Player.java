package blackjack.domain.participants;

public class Player extends Gamer {
    public Player(Name name) {
        super(name);
    }

    public Outcome checkResult(int dealerScore, boolean isDealerBlackjack) {
        return checkResultWhenBust(dealerScore, isDealerBlackjack);
    }

    private Outcome checkResultWhenBust(int dealerScore, boolean isDealerBlackjack) {
        if (calculateScore() > BLACKJACK_SCORE) {
            return Outcome.LOSE;
        }
        if (dealerScore > BLACKJACK_SCORE) {
            return Outcome.WIN;
        }
        return checkResultWhenBlackjack(dealerScore, isDealerBlackjack);
    }

    private Outcome checkResultWhenBlackjack(int dealerScore, boolean isDealerBlackjack) {
        if (isBlackjack() && isDealerBlackjack) {
            return Outcome.TIE;
        }
        if (isBlackjack()) {
            return Outcome.BLACKJACK_WIN;
        }
        return checkResultWhenNormal(dealerScore);
    }

    private Outcome checkResultWhenNormal(int dealerScore) {
        if (calculateScore() < dealerScore) {
            return Outcome.LOSE;
        }
        if (calculateScore() > dealerScore) {
            return Outcome.WIN;
        }
        return Outcome.TIE;
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
