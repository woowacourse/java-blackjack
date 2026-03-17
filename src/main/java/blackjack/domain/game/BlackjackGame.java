package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.card.ShuffledDeck;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.PlayerGroup;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private static final int INITIAL_DEAL_COUNT = 2;

    private final Deck deck;
    private final Dealer dealer;
    private final PlayerGroup playerGroup;

    public BlackjackGame(Deck deck, Dealer dealer, PlayerGroup playerGroup) {
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
        return playerGroup.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void initialDeal() {
        for (int i = 0; i < INITIAL_DEAL_COUNT; i++) {
            dealer.hitFrom(deck);
            playerGroup.deal(deck);
        }
    }

    public boolean canHit(Player player) {
        return player.canHit();
    }

    public void hit(Player player) {
        player.hitFrom(deck);
    }

    public int playDealerTurn() {
        int hitCount = 0;
        while (dealer.canHit()) {
            dealer.hitFrom(deck);
            hitCount++;
        }
        return hitCount;
    }

    public Map<Player, Long> calculatePlayerProfits() {
        return playerGroup.calculatePlayersProfit(dealer);
    }
}
