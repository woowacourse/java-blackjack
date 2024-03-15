package blackjack.domain.participants;

import blackjack.domain.participants.GamerInformation.GamblingMoney;
import blackjack.domain.participants.GamerInformation.Name;

public class Player extends Gamer {
    public Player(Name name) {
        super(name);
    }

    public Outcome checkOutcome(int dealerScore, boolean isDealerBlackjack) {
        return checkOutcomeWhenBust(dealerScore, isDealerBlackjack);
    }

    private Outcome checkOutcomeWhenBust(int dealerScore, boolean isDealerBlackjack) {
        if (calculateScore() > BLACKJACK_SCORE) {
            return Outcome.LOSE;
        }
        if (dealerScore > BLACKJACK_SCORE) {
            return Outcome.WIN;
        }
        return checkOutcomeWhenBlackjack(dealerScore, isDealerBlackjack);
    }

    private Outcome checkOutcomeWhenBlackjack(int dealerScore, boolean isDealerBlackjack) {
        if (isBlackjack() && isDealerBlackjack) {
            return Outcome.TIE;
        }
        if (isBlackjack()) {
            return Outcome.BLACKJACK_WIN;
        }
        return checkOutcomeWhenNormal(dealerScore);
    }

    private Outcome checkOutcomeWhenNormal(int dealerScore) {
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
