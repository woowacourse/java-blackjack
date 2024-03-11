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

//    public Map<GameResult, Integer> getDealerResult() {
//        return Map.of(
//                GameResult.WIN, getDealerWinCount(),
//                GameResult.DRAW, getDealerDrawCount(),
//                GameResult.LOSE, getDealerLoseCount()
//        );
//    }
//
//    private int getDealerWinCount() {
//        return (int) resultBoard.values().stream()
//                .filter(GameResult::isLose)
//                .count();
//    }
//
//    private int getDealerLoseCount() {
//        return (int) resultBoard.values().stream()
//                .filter(GameResult::isWin)
//                .count();
//    }
//
//    private int getDealerDrawCount() {
//        return (int) resultBoard.values().stream()
//                .filter(GameResult::isDraw)
//                .count();
//    }
}
