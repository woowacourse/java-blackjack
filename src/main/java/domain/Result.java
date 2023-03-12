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
        if (dealer.isBlackjack()) {
            calculateDealerBlackjack(players);
            return;
        }
        for (Player player : players.getPlayers()) {
            calculateWinLose(dealer, player);
        }
    }

    private void calculateDealerBlackjack(Players players) {
        for (Player player : players.getPlayers()) {
            drawIfPlayerBlackjack(player);
        }
    }

    private void drawIfPlayerBlackjack(Player player) {
        if (player.isBlackjack()) {
            gameResult.put(player.getName(), GameResult.DRAW);
            return;
        }
        gameResult.put(player.getName(), GameResult.LOSE);
    }

    private void calculateWinLose(Dealer dealer, Player player) {
        blackjack(player);
        win(dealer, player);
        lose(dealer, player);
        draw(dealer, player);
    }

    private void blackjack(Player player) {
        if (player.isBlackjack()) {
            gameResult.put(player.getName(), GameResult.BLACKJACK);
        }
    }

    private void win(Dealer dealer, Player player) {
        if (isPlayerNotBustAndWin(dealer, player)) {
            gameResult.put(player.getName(), GameResult.WIN);
        }
    }

    private boolean isPlayerNotBustAndWin(Dealer dealer, Player player) {
        return player.isNotBust() && player.isNotBlackjack() && (dealer.isBust() || player.getTotalScore()
                .isGreaterThan(dealer.getTotalScore()));
    }

    private void lose(Dealer dealer, Player player) {
        if (isPlayerBustOrLose(dealer, player)) {
            gameResult.put(player.getName(), GameResult.LOSE);
        }
    }

    private boolean isPlayerBustOrLose(Dealer dealer, Player player) {
        return player.isBust() || dealer.isNotBust() && dealer.getTotalScore().isGreaterThan(player.getTotalScore());
    }

    private void draw(Dealer dealer, Player player) {
        if (isBothBustOrDraw(dealer, player)) {
            gameResult.put(player.getName(), GameResult.DRAW);
        }
    }

    private boolean isBothBustOrDraw(Dealer dealer, Player player) {
        return player.isNotBust() && dealer.isNotBust() && dealer.getTotalScore().equals(player.getTotalScore());
    }

    public Map<Name, GameResult> getPlayersWinResult() {
        return gameResult;
    }

}
