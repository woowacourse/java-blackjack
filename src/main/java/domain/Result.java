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
                && !player.isBust() 
                && dealerIsBustOrDealerScoreLessThanPlayerScore(dealer, player);
    }

    private boolean dealerIsBustOrDealerScoreLessThanPlayerScore(Dealer dealer, Player player) {
        return dealer.isBust() || dealer.getTotalScoreValue() < player.getTotalScoreValue();
    }

    private boolean isPlayerLose(Dealer dealer, Player player) {
        return dealerAloneIsBlackJack(player, dealer)
                || dealerIsNotBustAndPlayerIsBustOrPlayerScoreLessThanDealerScore(dealer, player);
    }

    private boolean dealerIsNotBustAndPlayerIsBustOrPlayerScoreLessThanDealerScore(Dealer dealer, Player player) {
        return !dealer.isBust() && playerIsBustOrPlayerScoreLessThanDealerScore(dealer, player);
    }

    private boolean playerIsBustOrPlayerScoreLessThanDealerScore(Dealer dealer, Player player) {
        return player.isBust() || dealer.getTotalScoreValue() > player.getTotalScoreValue();
    }

    private boolean isPlayerDraw(Dealer dealer, Player player) {
        return dealerAndPlayerAreBlackJack(player, dealer)
                || dealerAndPlayerAreBust(dealer, player)
                || dealerAndPlayerHasSameScoreAndAreNotBlackJack(dealer, player);
    }

    private boolean dealerAndPlayerHasSameScoreAndAreNotBlackJack(Dealer dealer, Player player) {
        return dealer.getTotalScoreValue() == player.getTotalScoreValue() && dealerAndPlayerAreNotBlackJack(player, dealer);
    }

    private boolean dealerAndPlayerAreBust(Dealer dealer, Player player) {
        return dealer.isBust() && player.isBust();
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
