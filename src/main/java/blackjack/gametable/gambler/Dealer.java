package blackjack.gametable.gambler;

import blackjack.constant.MatchResult;
import blackjack.gametable.BlackjackRule;
import blackjack.gametable.card.Card;
import java.util.List;

public class Dealer extends Gambler {

    public static final int DEALER_HIT_THRESHOLD = 16;

    @Override
    public List<Card> openInitialCards() {
        return cards.openDealerInitialCards();
    }

    public void updateBetAmounts(Players players) {
        double dealerAmount = 0;

        for (Player player : players.getPlayers()) {
            MatchResult matchResult = BlackjackRule.evaluate(this, player);
            double profit = calculateProfit(matchResult, player.getBetAmount());
            player.updateBetAmount(profit);
            dealerAmount -= profit;
        }

        super.updateBetAmount(dealerAmount);
    }

    public boolean shouldDrawCard() {
        return sumCardScores() <= DEALER_HIT_THRESHOLD;
    }

    private double calculateProfit(MatchResult matchResult, double betAmount) {
        return matchResult.calculatePayout(betAmount);
    }

}
