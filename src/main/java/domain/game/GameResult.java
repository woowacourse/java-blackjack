package domain.game;

import domain.user.Dealer;
import domain.user.DealerStatus;
import domain.user.Player;
import domain.user.PlayerStatus;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private final Map<String, Boolean> playerResults = new LinkedHashMap<>();
    private final Map<Boolean, Integer> dealerResult = new HashMap<>();

    public GameResult(List<String> playerNames) {
        playerNames.forEach(name -> playerResults.put(name, false));
        dealerResult.put(true, 0);
        dealerResult.put(false, 0);
    }

    public void saveResults(Dealer dealer, List<Player> players) {
        judgeWinner(dealer, players);
    }

    public void judgeWinner(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            compareDealerStatusWithPlayer(dealer, player);
        }
    }

    public Map<String, Boolean> getPlayerResults() {
        return new LinkedHashMap<>(playerResults);
    }

    public Map<Boolean, Integer> getDealerResult() {
        return new HashMap<>(dealerResult);
    }

    private void compareDealerStatusWithPlayer(Dealer dealer, Player player) {
        if (player.isUserStatus(PlayerStatus.BUST)) {
            dealerWin(player);
            return;
        }
        if (player.isUserStatus(PlayerStatus.NORMAL) && dealer.isUserStatus(DealerStatus.BUST)) {
            playerWin(player);
            return;
        }
        compareScore(dealer, player);
    }

    private void compareScore(Dealer dealer, Player player) {
        if (player.getScore().isLessThanOrEqual(dealer.getScore())) {
            dealerWin(player);
            return;
        }
        playerWin(player);
    }

    private void playerWin(Player player) {
        dealerResult.put(false, dealerResult.get(false)+1);
        playerResults.put(player.getName(), true);
    }

    private void dealerWin(Player player) {
        dealerResult.put(true, dealerResult.get(true)+1);
        playerResults.put(player.getName(), false);
    }
}
