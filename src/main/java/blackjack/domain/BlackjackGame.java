package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private final List<Player> players;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    public BlackjackGame(List<Player> players, Dealer dealer, CardDeck cardDeck) {
        this.players = players;
        this.dealer = dealer;
        this.cardDeck = cardDeck;
    }

    public void distributeInitialCards() {
        for (Player player : players) {
            player.receiveCard(cardDeck.drawCard());
            player.receiveCard(cardDeck.drawCard());
        }
        dealer.receiveCard(cardDeck.drawCard());
        dealer.receiveCard(cardDeck.drawCard());
    }

    public boolean canHit(Player player) {
        return player.canHit();
    }

    public void playerHit(Player player) {
        if (!player.canHit()) {
            return;
        }
        player.receiveCard(cardDeck.drawCard());
    }

    public void dealerTurn() {
        while (dealer.canHit()) {
            dealer.receiveCard(cardDeck.drawCard());
        }
    }

    public Map<Player, Integer> calculatePlayersProfit() {
        HashMap<Player, Integer> playersProfit = new HashMap<>();
        for (Player player : players) {
            GameResult playerResult = dealer.informResultTo(player);
            int profit = player.calculateProfit(playerResult);
            playersProfit.put(player, profit);
        }
        return playersProfit;
    }

    public int calculateDealerProfit() {
        Map<Player, Integer> playersProfit = calculatePlayersProfit();
        return dealer.calculateProfit(playersProfit);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

}

