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
            player.startGame(cardDeck.drawCard(), cardDeck.drawCard());
        }
        dealer.startGame(cardDeck.drawCard(), cardDeck.drawCard());
    }

    public void playerHit(Player player) {
        player.hit(cardDeck.drawCard());
    }

    public void dealerTurn() {
        dealer.playTurn(cardDeck);
    }

    public Map<Player, Integer> calculatePlayersProfit() {
        HashMap<Player, Integer> playersProfit = new HashMap<>();
        for (Player player : players) {
            int profit = player.calculateProfit(dealer.getState());
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

