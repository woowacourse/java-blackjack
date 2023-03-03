package blackjack.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private List<Result> dealerResults = new ArrayList<>();
    private Map<Player, Result> playersResults = new HashMap<>();

    public GameResult(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            decideWinner(dealer, player);
        }
    }

    private void decideWinner(Dealer dealer, Player player) {
        if (!isBurst(player) && !isBurst(dealer)) {
            compareScore(player, dealer);
        }
        compareBuster(dealer, player);
    }

    private boolean isBurst(Participant participant) {
        return participant.getScore() > 21;
    }

    private void compareScore(Player player, Dealer dealer) {
        if (player.getScore() == dealer.getScore()) {
            bothDraw(player);
            return;
        }
        if (player.getScore() > dealer.getScore()) {
            playerWin(player);
            return;
        }
        dealerWin(player);
    }

    private void compareBuster(Dealer dealer, Player player) {
        if (isBurst(player)) {
            dealerWin(player);
            return;
        }
        if (isBurst(dealer)) {
            playerWin(player);
        }
    }

    private void dealerWin(Player player) {
        playersResults.put(player, Result.LOSE);
        dealerResults.add(Result.WIN);
    }

    private void playerWin(Player player) {
        playersResults.put(player, Result.WIN);
        dealerResults.add(Result.LOSE);
    }

    private void bothDraw(Player player) {
        playersResults.put(player, Result.DRAW);
        dealerResults.add(Result.DRAW);
    }

    public Result getPlayerResult(Player player) {
        return playersResults.get(player);
    }

    public List<Result> getDealerResults() {
        return dealerResults;
    }
}
