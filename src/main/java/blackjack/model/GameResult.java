package blackjack.model;

import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;
import blackjack.model.player.Gamers;
import blackjack.model.player.Player;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Map<Player, Profit> playersResult;

    public GameResult(Map<Player, Profit> playersResult) {
        this.playersResult = playersResult;
    }

    public static Profit calculateProfit(Gamer gamer, Result result) {
        if (isPossibleToGetAdditionalMoney(gamer, result)) {
            return Profit.of(gamer.getBetting(), result, true);
        }
        return Profit.of(gamer.getBetting(), result, false);
    }

    private static boolean isPossibleToGetAdditionalMoney(Gamer gamer, Result result) {
        return result == Result.WIN && gamer.isBlackjack();
    }

    public static Profit calculateDealerResult(List<Profit> profits) {
        return new Profit((int) (profits.stream()
                .mapToInt(Profit::getAmount)
                .sum()) * -1);
    }

    public Map<Player, Profit> getPlayersResult() {
        return new LinkedHashMap<>(playersResult);
    }
}
