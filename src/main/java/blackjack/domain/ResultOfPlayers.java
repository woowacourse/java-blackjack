package blackjack.domain;

import blackjack.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class ResultOfPlayers {

    private final Map<String, Pair<WinOrLose, Integer>> gameResultOfPlayers;

    public ResultOfPlayers(Map<String, Pair<WinOrLose, Integer>> gameResultOfPlayers) {
        this.gameResultOfPlayers = gameResultOfPlayers;
    }

    public Pair<List<String>, Integer> getDealerResult() {
        return new Pair<>(calculateDealerResult(), calculateDealerRevenue());
    }

    private int calculateDealerRevenue() {
        return -getDealerLoss();
    }

    private int getDealerLoss() {
        return gameResultOfPlayers.values().stream()
                .map(Pair::getValue)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private List<String> calculateDealerResult() {
        return gameResultOfPlayers.values().stream()
                .map(Pair::getKey)
                .map(WinOrLose::reverse)
                .map(WinOrLose::getMessage)
                .collect(toList());
    }

    public Map<String, Pair<String, Integer>> getGamerResult() {
        return gameResultOfPlayers.entrySet().stream()
                .collect(toMap(
                        Map.Entry::getKey,
                        getGamersGameResultAsStringAndRevenue())
                );
    }

    private Function<Map.Entry<String, Pair<WinOrLose, Integer>>, Pair<String, Integer>>
    getGamersGameResultAsStringAndRevenue() {
        return entry -> new Pair<>(
                entry.getValue().getKey().getMessage(),
                entry.getValue().getValue());
    }

}
