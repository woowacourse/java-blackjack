package blackjack.model.gamer;

import blackjack.model.wallet.DealerBetWallet;

public class Dealer extends Gamer {

    private final DealerBetWallet dealerBetWallet;

    private Dealer(int playersBetAmount) {
        this.dealerBetWallet = DealerBetWallet.from(playersBetAmount);
    }

    public static Dealer from(int playersBetAmount) {
        return new Dealer(playersBetAmount);
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
