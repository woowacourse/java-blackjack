package blackjack.user;

import blackjack.card.Card;
import blackjack.game.GameResult;
import java.util.List;

public class Player extends Participant {

    private static final int PLAYER_DISTRIBUTE_CARD_THRESHOLD = 21;

    private final PlayerName playerName;
    private Wallet wallet;

    public Player(final PlayerName playerName, final Wallet wallet) {
        this.playerName = playerName;
        this.wallet = wallet;
    }

    @Override
    public List<Card> openInitialCards() {
        return super.cards;
    }

    @Override
    public boolean isPossibleToAdd() {
        return super.calculateDenominations() < PLAYER_DISTRIBUTE_CARD_THRESHOLD;
    }

    public int updateWalletByGameResult(final GameResult gameResult) {
        this.wallet = wallet.calculateProfit(gameResult, isBlackjack());
        return wallet.getProfit();
    }

    public String getName() {
        return playerName.getName();
    }

    public int getProfit() {
        return wallet.getProfit();
    }
}
