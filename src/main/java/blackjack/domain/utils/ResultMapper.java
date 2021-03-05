package blackjack.domain.utils;

import blackjack.domain.gamer.Players;
import blackjack.domain.ResultType;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ResultMapper {

    public static Map<ResultType, Integer> resultOfDealer(Dealer dealer, Players players) {
        Map<ResultType, Integer> result = new EnumMap<>(ResultType.class);
        for (Player player : players) {
            ResultType switchedResult = ResultCalculator.decideWinner(player, dealer)
                    .switchPosition();
            result.put(switchedResult, result.getOrDefault(switchedResult, 0) + 1);
        }
        return result;
    }

    public static Map<String, ResultType> resultOfPlayers(Dealer dealer, Players players) {
        Map<String, ResultType> result = new HashMap<>();
        for (Player player : players) {
            ResultType resultType = ResultCalculator.decideWinner(player, dealer);
            result.put(player.getName(), resultType);
        }
        return result;
    }
}
