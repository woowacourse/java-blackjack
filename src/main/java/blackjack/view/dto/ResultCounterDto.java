package blackjack.view.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.Result;
import java.util.List;
import java.util.Map;

public class ResultCounterDto {

    private final Map<Player, Result> resultCounter;
    private final Dealer dealer;
    private final List<Player> players;

    private ResultCounterDto(Map<Player, Result> resultCounter, Dealer dealer, List<Player> players) {
        this.resultCounter = resultCounter;
        this.dealer = dealer;
        this.players = players;
    }

    public static ResultCounterDto of(Map<Player, Result> resultCounter, Dealer dealer, List<Player> players) {
        return new ResultCounterDto(resultCounter, dealer, players);
    }

    public String showDealerResult() {
        return dealer.getName().getValue() + ": " +
                resultCounter.values().stream().filter(result -> result == Result.LOSE).count() + "승" +
                resultCounter.values().stream().filter(result -> result == Result.TIE).count() + "무" +
                resultCounter.values().stream().filter(result -> result == Result.WIN).count() + "패";
    }

    public String showEachPlayersResult() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : resultCounter.keySet()) {
            stringBuilder.append(player.getName().getValue() + ": " + resultCounter.get(player).getResult());
            stringBuilder.append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }
}
