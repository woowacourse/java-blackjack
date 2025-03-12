package blackjack.domain;

import blackjack.domain.card.CardDump;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private final List<Player> players;
    private final Dealer dealer;
    private final CardDump cardDump;

    public BlackjackGame(List<Player> players, Dealer dealer, CardDump cardDump) {
        this.players = players;
        this.dealer = dealer;
        this.cardDump = cardDump;
    }

    public void distributeInitialCards() {
        for (Player player : players) {
            player.receiveCard(cardDump.drawCard());
            player.receiveCard(cardDump.drawCard());
        }
        dealer.receiveCard(cardDump.drawCard());
        dealer.receiveCard(cardDump.drawCard());
    }

    public boolean canHit(Player player) {
        return player.canHit();
    }

    public void playerHit(Player player) {
        if (!player.canHit()) {
            return;
        }
        player.receiveCard(cardDump.drawCard());
    }

    public void dealerTurn() {
        while (dealer.canHit()) {
            dealer.receiveCard(cardDump.drawCard());
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
        Map<Player, Integer> playersProfit = calculatePlayersProfit(); // 예: 딜러의 멤버변수 players
        int totalProfit = playersProfit.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        return -totalProfit;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

}

