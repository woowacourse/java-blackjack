package model.result;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
        if (blackjackCase(player, blackjack) && !blackjack.getDealer()) {
            return GameResult.BLACKJACK;
        }
        if (calculatePlayerWin(dealer, player)) {
            return GameResult.WIN;
        }
        if (blackjack.getDealer() && !blackjackCase(player, blackjack) || calculatePlayerFail(dealer, player)) {
            return GameResult.FAIL;
        }
        return GameResult.DRAW;
    }

    private boolean blackjackCase(Player player, Blackjack blackjack) {
        boolean playerBlackjack = false;
        for (Entry<Player, Boolean> entrySet : blackjack.getPlayersStatus().entrySet()) {
            if (entrySet.getKey().getName().equals(player.getName())) {
                playerBlackjack = entrySet.getValue();
            }
        }
        return playerBlackjack;
    }

    private boolean calculatePlayerWin(Dealer dealer, Player player) {
        return dealer.totalNumber() < player.totalNumber() && player.isNotBust() || dealer.isBust();
    }

    private boolean calculatePlayerFail(Dealer dealer, Player player) {
        return dealer.totalNumber() > player.totalNumber() || player.isBust();
    }

    public Map<String, GameResult> getPlayerResult() {
        return playerResult;
    }

    public GameResult getResult(Player player) {
        return playerResult.get(player.getName());
    }
}
