package blackjack.domain.participant;

import blackjack.domain.result.Result;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Dealer extends Participant {
    private static final int MAX_SUM_FOR_MORE_CARD = 16;
    private static final String FIXED_DEALER_NAME = "딜러";

    private final DealerMoney dealerMoney = new DealerMoney();

    public Dealer() {
        super(FIXED_DEALER_NAME);
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

    public Map<Result, Integer> generateEveryResult(final Players players) {
        final Map<Result, Integer> dealerResult = new HashMap<>();
        for (Player player : players.getPlayers()) {
            Result result = this.generateResult(player);
            dealerResult.put(result, dealerResult.getOrDefault(result, 0) + 1);
        }
        return Collections.unmodifiableMap(dealerResult);
    }

    public void calculateProfit(final Map<Player, Double> playerProfit) {
        for (Player player : playerProfit.keySet()) {
            dealerMoney.calculateByOpponentProfit(playerProfit.get(player));
        }
    }

    public DealerMoney getDealerMoney() {
        return dealerMoney;
    }
}
