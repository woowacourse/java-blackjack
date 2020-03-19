package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private static final double PLAYER_FIRST_WIN_RATIO = 1.5;
    private static final double PLAYER_WIN_RATIO = 2.0;
    private static final double PLAYER_LOSE_RATIO = -1.0;
    private static final int DEALER_RESULT_RATIO = -1;

    private final Map<String, Money> playerResult = new HashMap<>();

    public GameResult(Players players, Dealer dealer) {
        makeResult(players, dealer);
    }

    private void makeResult(Players players, Dealer dealer) {
        for (Player player : players.get()) {
            playerResult.put(player.getName(), makeResultMoney(dealer, player));
        }
    }

    private Money makeResultMoney(Dealer dealer, Player player) {
        if (player.isBlackJackByFirstCards()) {
            return player.getMoney().multiply(PLAYER_FIRST_WIN_RATIO);
        }
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return player.getMoney();
        }
        if (dealer.isWinner(player)) {
            return player.getMoney().multiply(PLAYER_LOSE_RATIO);
        }
        return player.getMoney().multiply(PLAYER_WIN_RATIO);
    }

    public int getDealerMoney() {
        return DEALER_RESULT_RATIO * playerResult.keySet()
                .stream()
                .map(playerResult::get)
                .mapToInt(Money::getValue)
                .sum();
    }

    public Map<String, Money> getPlayerResult() {
        return Collections.unmodifiableMap(playerResult);
    }
}
