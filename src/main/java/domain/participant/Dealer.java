package domain.participant;

import java.util.HashMap;
import java.util.Map;

import domain.betting.BettingMoney;
import domain.profit.FinalProfit;
import util.Constants;

public class Dealer extends Participant {
    private static final int LIMIT_TAKE_CARD_VALUE = 17;
    private static final int LOSE_VALUE = -1;
    private static final double BLACKJACK_VALUE = 1.5;

    private final Map<Player, BettingMoney> bettingMoneyByPlayer;
    private final Map<Player, FinalProfit> finalProfitByPlayer;

    public Dealer(HandCards handCards) {
        super(new Name(Constants.DEALER_NAME), handCards);
        this.bettingMoneyByPlayer = new HashMap<>();
        this.finalProfitByPlayer = new HashMap<>();
    }

    public boolean checkCardsCondition() {
        return getOptimalCardValueSum() < LIMIT_TAKE_CARD_VALUE;
    }

    public void savePlayerBettingMoney(Player player, BettingMoney bettingMoney) {
        bettingMoneyByPlayer.put(player, bettingMoney);
    }

    public void calculateFinalProfit(Player player) {
        int playerCardValueSum = player.getOptimalCardValueSum();
        int dealerCardValueSum = this.getOptimalCardValueSum();
        BettingMoney bettingMoney = bettingMoneyByPlayer.get(player);
        if (playerCardValueSum > 21) {
            finalProfitByPlayer.put(player, new FinalProfit(bettingMoney.getValue() * LOSE_VALUE));
        }
        if (dealerCardValueSum > 21) {
            finalProfitByPlayer.put(player, new FinalProfit(bettingMoney.getValue()));
        }
        if (dealerCardValueSum == 21 && playerCardValueSum == 21) {
            finalProfitByPlayer.put(player, new FinalProfit(bettingMoney.getValue()));
        }
        if (dealerCardValueSum == 21 && playerCardValueSum < 21) {
            finalProfitByPlayer.put(player, new FinalProfit(bettingMoney.getValue() * LOSE_VALUE));
        }
        if (dealerCardValueSum != 21 && playerCardValueSum == 21) {
            finalProfitByPlayer.put(player, new FinalProfit(bettingMoney.getValue() * BLACKJACK_VALUE));
        }
    }

    public Map<Player, BettingMoney> getBettingMoneyByPlayer() {
        return Map.copyOf(bettingMoneyByPlayer);
    }

    public Map<Player, FinalProfit> getFinalProfitByPlayer() {
        return Map.copyOf(finalProfitByPlayer);
    }
}
