package blackjack.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GameProfitBoard {
    private final Map<String, Integer> resultBoard = new HashMap<>();

    public GameProfitBoard(Dealer dealer, Players players) {
        Map<Player, GameResult> compareResult = players.compareEach(dealer.getScore());
        compareResult.entrySet().forEach(this::addProfitResult);
    }

    private void addProfitResult(Entry<Player, GameResult> entry) {
        Player player = entry.getKey();
        GameResult gameResult = entry.getValue();
        int playerProfit = calcPlayerProfit(player, gameResult);
        resultBoard.put(player.getName(), playerProfit);
    }

    private int calcPlayerProfit(Player player, GameResult gameResult){
        return (int) (player.getBetAmount() * gameResult.getProfitRate());
    }

    public int getProfitOf(Player player) {
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
