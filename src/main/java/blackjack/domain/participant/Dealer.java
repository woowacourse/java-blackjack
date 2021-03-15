package blackjack.domain.participant;

import blackjack.domain.result.Result;

import java.util.Map;

public class Dealer extends Participant {
    private static final int MAX_SUM_FOR_MORE_CARD = 16;
    private static final String FIXED_DEALER_NAME = "딜러";

    public Dealer() {
        super(FIXED_DEALER_NAME, 0);
    }

    public boolean canGetMoreCard() {
        return hand.calculateScore() <= MAX_SUM_FOR_MORE_CARD;
    }

    @Override
    public Result generateResult(final Participant participant) {
        if (this.isBlackjack() && !participant.isBlackjack()) {
            return Result.BLACKJACK_WIN;
        }
        if (participant.isBust()) {
            return Result.WIN;
        }
        if (this.isBust()) {
            return Result.LOSE;
        }
        return generateResultByScore(participant);
    }

    public void calculateProfit(final Map<Player, Integer> opponentProfit) {
        for (Player player : opponentProfit.keySet()) {
            money = money.updateMoneyByProfit(opponentProfit.get(player) * -1);
        }
    }

    public Money getDealerMoney() {
        return money;
    }
}
