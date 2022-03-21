package domain.result;

import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class Results {

    private static final String ABSENT_NAME_ERROR_MESSAGE_FORMAT = "[Error] \"%s\" : 이름이 존재하지 않습니다.";

    private final Map<Name, WinOrLose> playerResults;

    private Results(Map<Name, WinOrLose> playerResults) {
        this.playerResults = Map.copyOf(playerResults);
    }

    public static Results generateResults(Dealer dealer, Players players) {
        Map<Name, WinOrLose> playerResult = new LinkedHashMap<>();
        players.forEach(player -> playerResult.put(player.getName(), WinOrLose.compareWinOrLose(dealer, player)));
        return new Results(playerResult);
    }

    public WinOrLose getVersusOfPlayer(Name name) {
        if (!playerResults.containsKey(name)) {
            throw new IllegalArgumentException(String.format(ABSENT_NAME_ERROR_MESSAGE_FORMAT, name.getValue()));
        }
        return playerResults.get(name);
    }

    public int countDealerWin() {
        return (int) playerResults.keySet().stream()
                .filter(key -> playerResults.get(key) == WinOrLose.LOSE)
                .count();
    }

    public int countDealerDraw() {
        return (int) playerResults.keySet().stream()
                .filter(key -> playerResults.get(key) == WinOrLose.DRAW)
                .count();
    }

    public int countDealerLose() {
        return (int) playerResults.keySet().stream()
                .filter(key -> playerResults.get(key) == WinOrLose.WIN)
                .count();
    }
}
