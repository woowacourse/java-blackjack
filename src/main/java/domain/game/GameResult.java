package domain.game;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private final Map<String, Money> playerResult;

    public GameResult(final Players players, final Dealer dealer) {
        playerResult = makeResult(players, dealer);
    }

    private Map<String, Money> makeResult(Players players, Dealer dealer) {
        Map<String, Money> result = new HashMap<>();
        for (Player player : players.get()) {
            result.put(player.getName(), makeResultMoney(dealer, player));
        }
        return result;
    }

    private Money makeResultMoney(Dealer dealer, Player player) {
        if (player.isBlackJackByFirstCards()) {
            return player.getMoney().multiply(Rule.getPlayerFirstWinRatio());
        }
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return player.getMoney();
        }
        if (dealer.isWinner(player)) {
            return player.getMoney().multiply(Rule.getPlayerLoseRatio());
        }
        return player.getMoney().multiply(Rule.getPlayerWinRatio());
    }

    public int getDealerMoney() {
        return Rule.getDealerResultRatio() * playerResult.keySet()
                .stream()
                .map(playerResult::get)
                .mapToInt(Money::getValue)
                .sum();
    }

    public Map<String, Money> getPlayerResult() {
        return Collections.unmodifiableMap(playerResult);
    }
}
