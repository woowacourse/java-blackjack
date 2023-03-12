package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private final Map<Player, GameResult> gameResult;

    public Result(Dealer dealer, List<Player> players) {
        gameResult = new LinkedHashMap<>();
        calculate(dealer, players);
    }

    private void calculate(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            calculateWinLose(dealer, player);
        }
    }

    private void calculateWinLose(Dealer dealer, Player player) {
        blackJackWin(dealer, player);
        win(dealer, player);
        lose(dealer, player);
        draw(dealer, player);
    }

    private void blackJackWin(Dealer dealer, Player player) {
        if (isPlayerBlackJackWin(player, dealer)) {
            gameResult.put(player, GameResult.BLACK_JACK_WIN);
        }
    }

    private void win(Dealer dealer, Player player) {
        if (isPlayerWin(dealer, player)) {
            gameResult.put(player, GameResult.WIN);
        }
    }

    private void lose(Dealer dealer, Player player) {
        if (isPlayerLose(dealer, player)) {
            gameResult.put(player, GameResult.LOSE);
        }
    }

    private void draw(Dealer dealer, Player player) {
        if (isPlayerDraw(dealer, player)) {
            gameResult.put(player, GameResult.DRAW);
        }
    }

    private boolean isPlayerBlackJackWin(Player player, Dealer dealer) {
        return player.isBlackJack() && !dealer.isBlackJack();
    }

    private boolean isPlayerWin(Dealer dealer, Player player) {
        return dealerAndPlayerAreNotBlackJack(player, dealer)
                && !player.isBust() && (dealer.isBust() || dealer.getTotalScoreValue() < player.getTotalScoreValue());
    }

    private boolean isPlayerLose(Dealer dealer, Player player) {
        return dealerAloneIsBlackJack(player, dealer)
                || (!dealer.isBust() && (player.isBust() || dealer.getTotalScoreValue() > player.getTotalScoreValue()));
    }

    private boolean isPlayerDraw(Dealer dealer, Player player) {
        return dealerAndPlayerAreBlackJack(player, dealer)
                || (dealer.isBust() && player.isBust())
                || (dealerAndPlayerAreNotBlackJack(player, dealer) && dealer.getTotalScoreValue() == player.getTotalScoreValue());
    }

    private boolean dealerAndPlayerAreNotBlackJack(Player player, Dealer dealer) {
        return !player.isBlackJack() && !dealer.isBlackJack();
    }

    private boolean dealerAloneIsBlackJack(Player player, Dealer dealer) {
        return !player.isBlackJack() && dealer.isBlackJack();
    }

    private boolean dealerAndPlayerAreBlackJack(Player player, Dealer dealer) {
        return player.isBlackJack() && dealer.isBlackJack();
    }

    public Map<Player, GameResult> getResult() {
        return gameResult;
    }

}
