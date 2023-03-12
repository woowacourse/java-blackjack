package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;

import java.util.*;

public class GameResult {
    public static final int MAX_BLACKJACK_SCORE = 21;
    public static final int INITIAL_SCORE = 0;

    private final Map<Result, Integer> dealerResults = new EnumMap<>(Result.class);
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
        return !participant.getHand().calculateScore().isNotOver(MAX_BLACKJACK_SCORE);
    }

    private void compareBuster(Dealer dealer, Player player) {
        if (isBust(player)) {
            addDealerWin(player);
            return;
        }
        if (isBust(dealer)) {
            addPlayerWin(player);
        }
    }

    private void compareScore(Dealer dealer, Player player) {
        Score dealerScore = dealer.getHand().calculateScore();
        Score playerScore = player.getHand().calculateScore();
        if (dealerScore.equals(playerScore)) {
            bothDraw(player);
            return;
        }
        if (dealerScore.isLessThan(playerScore)) {
            addPlayerWin(player);
            return;
        }
        addDealerWin(player);
    }

    private void addDealerWin(Player player) {
        playersResults.put(player, Result.LOSE);
        dealerResults.replace(Result.WIN, dealerResults.get(Result.WIN) + 1);
    }

    private void addPlayerWin(Player player) {
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
