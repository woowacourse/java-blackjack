package domain.game;

import domain.user.Dealer;
import domain.user.Name;
import domain.user.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private final Map<Name, Integer> playerPrizes = new LinkedHashMap<>();

    public GameResult(List<Name> playerNames) {
        playerNames.forEach(name -> playerPrizes.put(name, 0));
    }

    public void saveResults(Dealer dealer, List<Player> players) {
        judgeWinner(dealer, players);
    }

    public Map<Name, Integer> getPlayerPrizes() {
        return new LinkedHashMap<>(playerPrizes);
    }

    private void judgeWinner(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            compareDealerStatusWithPlayer(dealer, player);
        }
    }

    private void compareDealerStatusWithPlayer(Dealer dealer, Player player) {
        if (dealer.isBust() && player.getPrize() > 0) {
            playerPrizes.put(player.getName(), player.getBatting());
            return;
        }
        if (dealer.isBust() && player.getPrize() < 0) {
            playerPrizes.put(player.getName(), player.getPrize());
            return;
        }
        if (dealer.isBlackjack() && player.isBlackjack()) {
            playerPrizes.put(player.getName(), player.getBatting());
            return;
        }
        compareScore(dealer, player);
    }

    private void compareScore(Dealer dealer, Player player) {
        if (player.getScore().isLessThan(dealer.getScore())) {
            playerPrizes.put(player.getName(), player.getBatting() * -1);
            return;
        }
        if (player.getScore().equals(dealer.getScore())) {
            playerPrizes.put(player.getName(), 0);
            return;
        }
        playerPrizes.put(player.getName(), player.getPrize());
    }
}
