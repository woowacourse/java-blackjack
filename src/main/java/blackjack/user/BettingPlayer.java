package blackjack.user;

import blackjack.card.CardHand;
import blackjack.game.GameResult;

public class BettingPlayer extends Player {

    private Wallet wallet;

    public BettingPlayer(final PlayerName playerName, CardHand cards, final Wallet wallet) {
        super(playerName, cards);
        this.wallet = wallet;
    }

    public int updateWalletByGameResult(final GameResult gameResult) {
        this.wallet = wallet.calculateProfit(gameResult, cards.isBlackjack());
        return wallet.getProfit();
    }

    public int getProfit() {
        return wallet.getProfit();
    }
}
