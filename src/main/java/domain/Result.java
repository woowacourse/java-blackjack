package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {

    private final Map<Name, GameResult> gameResult;

    public Result(Dealer dealer, Players players) {
        gameResult = new LinkedHashMap<>();
        calculate(dealer, players);
    }

    private void calculate(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            calculateWinLose(dealer, player);
        }
    }

    private void calculateWinLose(Dealer dealer, Player player) {
        win(dealer, player);
        lose(dealer, player);
        draw(dealer, player);
    }

    private void win(Dealer dealer, Player player) {
        if (isPlayerNotBustAndWin(dealer, player)) {
            gameResult.put(player.getName(), GameResult.WIN);
        }
    }

    private boolean isPlayerNotBustAndWin(Dealer dealer, Player player) {
        return !player.isBust() && (dealer.isBust() || dealer.getTotalScore() < player.getTotalScore());
    }

    private void lose(Dealer dealer, Player player) {
        if (isPlayerBustOrLose(dealer, player)) {
            gameResult.put(player.getName(), GameResult.LOSE);
        }
    }

    private boolean isPlayerBustOrLose(Dealer dealer, Player player) {
        return !dealer.isBust() && (player.isBust() || dealer.getTotalScore() > player.getTotalScore());
    }

    private void draw(Dealer dealer, Player player) {
        if (isBothBustOrDraw(dealer, player)) {
            gameResult.put(player.getName(), GameResult.DRAW);
        }
    }

    private boolean isBothBustOrDraw(Dealer dealer, Player player) {
        return dealer.isBust() && player.isBust() || dealer.getTotalScore() == player.getTotalScore();
    }

    public Map<String, GameResult> getResult() {
        LinkedHashMap<String, GameResult> results = new LinkedHashMap<>();
        for (Name name : gameResult.keySet()) {
            results.put(name.getValue(), gameResult.get(name));
        }
        return results;
    }

}
