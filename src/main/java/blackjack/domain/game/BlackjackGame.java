package blackjack.domain.game;

import blackjack.domain.card.ShuffledDeck;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.PlayerGroup;
import blackjack.domain.participants.Profit;
import java.util.List;

public class BlackjackGame {
    private static final int INITIAL_DEAL_COUNT = 2;

    private final ShuffledDeck deck;
    private final Dealer dealer;
    private final PlayerGroup playerGroup;

    public BlackjackGame(ShuffledDeck deck, Dealer dealer, PlayerGroup playerGroup) {
        this.deck = deck;
        this.dealer = dealer;
        this.playerGroup = playerGroup;
    }

    public static BlackjackGame createBasic(PlayerGroup playerGroup) {
        return new BlackjackGame(
            ShuffledDeck.create(),
            Dealer.createEmptyHand(),
            playerGroup);
    }

    public List<Player> getPlayers() {
        return playerGroup.players();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void initialDeal() {
        for (int i = 0; i < INITIAL_DEAL_COUNT; i++) {
            dealer.hit(deck.draw());
            playerGroup.deal(deck);
        }
    }

    public boolean canHit(Player player) {
        return player.canHit();
    }

    public void hit(Player player) {
        player.hit(deck.draw());
    }

    public int playDealerTurn() {
        int hitCount = 0;
        while (dealer.canHit()) {
            dealer.hit(deck.draw());
            hitCount++;
        }
        return hitCount;
    }

    public Profit calculateProfit(Player player) {
        GameResult judge = BlackjackGameReferee.judge(dealer, player);
        return player.calculateProfit(judge);
    }
}
