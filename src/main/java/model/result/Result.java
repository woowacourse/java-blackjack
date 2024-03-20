package model.result;

import java.util.HashMap;
import java.util.Map;
import model.blackjackgame.Blackjack;
import model.participants.dealer.Dealer;
import model.participants.player.Player;
import model.participants.player.Players;

public class Result {

    private final Map<String, GameResult> playerResult;

    public Result(Dealer dealer, Players players, Blackjack blackjack) {
        this.playerResult = new HashMap<>();
        createPlayersResult(dealer, players, blackjack);
    }

    private void createPlayersResult(Dealer dealer, Players players, Blackjack blackjack) {
        for (Player player : players.getPlayers()) {
            playerResult.put(player.getName(), calculatePlayerResult(dealer, player, blackjack));
        }
    }

    private GameResult calculatePlayerResult(Dealer dealer, Player player, Blackjack blackjack) {
        if (isPlayerBlackjack(player, blackjack) && !blackjack.isDealerBlackjack()) {
            return GameResult.BLACKJACK;
        }
        if (isPlayerWin(dealer, player)) {
            return GameResult.WIN;
        }
        if (blackjack.isDealerBlackjack() && !isPlayerBlackjack(player, blackjack) || isPlayerFail(dealer, player)) {
            return GameResult.FAIL;
        }
        return GameResult.DRAW;
    }

    private boolean isPlayerBlackjack(Player player, Blackjack blackjack) {
        return blackjack.blackjackStatusOfPlayer(player);
    }

    private boolean isPlayerWin(Dealer dealer, Player player) {
        return dealer.totalNumber() < player.totalNumber() && player.isNotBust() || dealer.isBust();
    }

    private boolean isPlayerFail(Dealer dealer, Player player) {
        return dealer.totalNumber() > player.totalNumber() || player.isBust();
    }

    public Map<String, GameResult> getPlayerResult() {
        return playerResult;
    }

    public GameResult getResult(Player player) {
        return playerResult.get(player.getName());
    }
}
