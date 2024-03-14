package blackjack.model.gamer;

import blackjack.model.wallet.DealerBetWallet;

public class Dealer extends Gamer {

    private final DealerBetWallet dealerBetWallet;

    private Dealer(DealerBetWallet dealerBetWallet) {
        this.dealerBetWallet = dealerBetWallet;
    }

    public Dealer(int playersBetAmount) {
        this(new DealerBetWallet(playersBetAmount));
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
