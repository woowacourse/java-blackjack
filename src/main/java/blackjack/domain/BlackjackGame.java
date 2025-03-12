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

    public Map<GameResult, Integer> getDealerResult(final Dealer dealer, final List<Player> players) {
        Map<GameResult, Integer> gameFinalResult = new HashMap<>();
        for (Player player : players) {
            GameResult result = GameResult.checkDealerWin(player, dealer);
            gameFinalResult.put(result, gameFinalResult.getOrDefault(result, 0) + 1);
        }
        return gameFinalResult;
    }

    public GameResult getPlayerResult(final Player player, final Dealer dealer) {
        return GameResult.checkPlayerWin(player, dealer);
    }

    public Map<Player, Integer> calculatePlayersProfit() {
        Map<Player, Integer> playerProfits = new HashMap<>();
        for (Player player : players) {
            int profit = calculatePlayerProfit(player, dealer);
            playerProfits.put(player, profit);
        }
        return playerProfits;
    }

    public int calculatePlayerProfit(final Player player, final Dealer dealer) {
        GameResult playerResult = GameResult.checkPlayerWin(player, dealer);
        return player.calculateProfit(playerResult);
    }

    public int calculateDealerProfit() {
        int playersProfitSum = players.stream()
                .mapToInt(player -> calculatePlayerProfit(player, dealer))
                .sum();

        return - playersProfitSum;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

}

