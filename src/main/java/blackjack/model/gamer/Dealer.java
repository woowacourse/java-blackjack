package blackjack.model.gamer;

import blackjack.model.card.Card;
import blackjack.model.gameRule.GameRule;
import blackjack.model.wallet.DealerBetWallet;

public class Dealer extends Gamer {

    private final DealerBetWallet dealerBetWallet;

    private Dealer(DealerBetWallet dealerBetWallet) {
        this.dealerBetWallet = dealerBetWallet;
    }

    public Dealer(int playersBetAmount) {
        this(new DealerBetWallet(playersBetAmount));
    }

    public Card fistCard() {
        return allCards().get(0);
    }

    @Override
    public boolean canHit() {
        return totalScore() <= GameRule.DEALER_HIT_MAX_SCORE;
    }

    public void payPlayerProfit(Player player) {
        dealerBetWallet.registerPayoutAmount(player);
    }

    public int payoutAmount() {
        return dealerBetWallet.getPayoutAmount();
    }

    public int netProfit() {
        return dealerBetWallet.calculateNetProfit();
    }
}
