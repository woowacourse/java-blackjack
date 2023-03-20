package domain.game;

import domain.user.Dealer;
import domain.user.Name;
import domain.user.Player;
import domain.user.Players;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private final Map<Name, Integer> playerPrizes = new LinkedHashMap<>();

    public GameResult(List<Name> playerNames) {
        playerNames.forEach(name -> playerPrizes.put(name, 0));
    }

    public void saveResults(Dealer dealer, Players players) {
        judgeWinner(dealer, players);
    }

    private void judgeWinner(Dealer dealer, Players players) {
        for (Name playerName : players.getAllNames()) {
            compareDealerStatusWithPlayer(dealer, players.findPlayerByName(playerName));
        }
    }

    private void compareDealerStatusWithPlayer(Dealer dealer, Player player) {
        if (dealer.isBust() && player.getPrize() > 0) {
            playerPrizes.put(player.getName(), player.getBetting());
            return;
        }
        if (dealer.isBust() && player.getPrize() < 0) {
            playerPrizes.put(player.getName(), player.getPrize());
            return;
        }
        if (dealer.isBlackjack() && player.isBlackjack()) {
            playerPrizes.put(player.getName(), player.getBetting());
            return;
        }
        compareScore(dealer, player);
    }

    private void compareScore(Dealer dealer, Player player) {
        if (player.getScore().isLessThan(dealer.getScore())) {
            playerPrizes.put(player.getName(), player.getBetting() * -1);
            return;
        }
        if (player.getScore().equals(dealer.getScore())) {
            playerPrizes.put(player.getName(), 0);
            return;
        }
        playerPrizes.put(player.getName(), player.getPrize());
    }

    public Map<Name, Integer> getPlayerPrizes() {
        return new LinkedHashMap<>(playerPrizes);
    }
}
