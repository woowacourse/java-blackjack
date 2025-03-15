package blackjack.model;

import blackjack.model.betting.MatchResult;
import blackjack.model.betting.Profit;
import blackjack.model.card.Card;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private final Dealer dealer;
    private final Players players;

    public Game(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void dealInitialCards() {
        for (Player player : players.getPlayers()) {
            dealPlayerCard(player);
            dealPlayerCard(player);
        }
        dealDealerCard(dealer);
        dealDealerCard(dealer);
    }

    private void dealPlayerCard(Player player) {
        player.receiveHand(dealer.drawCard());
    }

    private void dealDealerCard(Dealer dealer) {
        dealer.receiveHand(dealer.drawCard());
    }

    public void hitPlayer(Player player) {
        dealPlayerCard(player);
    }

    public boolean hitDealer() {
        return dealer.hitDealer();
    }

    public Map<Player, Profit> calculatePlayersProfit() {
        Map<Player, Profit> results = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            MatchResult matchResult = MatchResult.calculatePlayerResult(dealer, player);
            results.put(player, player.calculateProfit(matchResult));
        }
        return results;
    }

    public Profit calculateDealerProfit(Map<Player, Profit> playersProfit) {
        return dealer.calculateProfit(playersProfit);
    }

    public Card getDealerVisibleCard() {
        return dealer.getVisibleCard();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<Card> getDealerHand() {
        return List.copyOf(dealer.getHand());
    }

    public int getDealerTotal() {
        return dealer.calculateHandTotal();
    }
}
