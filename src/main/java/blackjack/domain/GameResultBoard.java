package blackjack.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GameResultBoard {
    private final Map<String, Integer> resultBoard = new HashMap<>();

    public GameResultBoard(Dealer dealer, Players players) {
        Map<Player, GameResult> compareResult = players.compareEach(dealer.getScore());

        for (Entry<Player, GameResult> entry : compareResult.entrySet()) {
            Player player = entry.getKey();
            GameResult gameResult = entry.getValue();

            int playerProfit = (int) (gameResult.getProfitRate() * player.getBetAmount());

            resultBoard.put(player.getName(), playerProfit);
        }
    }

    public int getGameResult(Player player) {
        return resultBoard.get(player.getName());
    }

    public int getDealerProfit() {
        return makeNegative(playersTotalProfit());
    }

    private int playersTotalProfit() {
        return resultBoard.values().stream()
                .mapToInt(i -> i)
                .sum();
    }

    private int makeNegative(int value) {
        return -1 * value;
    }
}
