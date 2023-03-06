package blackjack.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    public static final int MAX_BLACKJACK_SCORE = 21;
    public static final int INITIAL_SCORE = 0;

    private final Map<Result, Integer> dealerResults = new HashMap<>();
    private final Map<Player, Result> playersResults = new HashMap<>();

    public GameResult(Dealer dealer, List<Player> players) {
        initDealerResults();

        for (Player player : players) {
            decideWinner(dealer, player);
        }
    }

    private void initDealerResults() {
        for (Result result : Result.values()) {
            dealerResults.put(result, INITIAL_SCORE);
        }
    }

    private void decideWinner(Dealer dealer, Player player) {
        if (isBust(player) || isBust(dealer)) {
            compareBuster(dealer, player);
            return;
        }
        compareScore(dealer, player);
    }

    private boolean isBust(Participant participant) {
        return participant.getScore() > MAX_BLACKJACK_SCORE;
    }

    private void compareBuster(Dealer dealer, Player player) {
        if (isBust(player)) {
            dealerWin(player);
            return;
        }
        if (isBust(dealer)) {
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
        dealerResults.replace(Result.WIN, dealerResults.get(Result.WIN) + 1);
    }

    private void playerWin(Player player) {
        playersResults.put(player, Result.WIN);
        dealerResults.replace(Result.LOSE, dealerResults.get(Result.LOSE) + 1);
    }

    private void bothDraw(Player player) {
        playersResults.put(player, Result.DRAW);
        dealerResults.replace(Result.DRAW, dealerResults.get(Result.DRAW) + 1);
    }

    public Result getPlayerResult(Player player) {
        return playersResults.get(player);
    }

    public Map<Result, Integer> getDealerResults() {
        return Collections.unmodifiableMap(dealerResults);
    }
}
