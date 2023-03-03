package blackjack.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    public static final int MAX_BLACKJACK_SCORE = 21;

    private final List<Result> dealerResults = new ArrayList<>();
    private final Map<Player, Result> playersResults = new HashMap<>();

    public GameResult(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            decideWinner(dealer, player);
        }
    }

    private void decideWinner(Dealer dealer, Player player) {
        if (isBurst(player) || isBurst(dealer)) {
            compareBuster(dealer, player);
        }
        compareScore(dealer, player);
    }

    private boolean isBurst(Participant participant) {
        return participant.getScore() > MAX_BLACKJACK_SCORE;
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

    private void compareScore(Dealer dealer, Player player) {
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
