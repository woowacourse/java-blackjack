package result.handResult;

import participant.Dealer;
import participant.Player;
import result.GameStatus;

public class ScoreComparison implements HandResult {
    @Override
    public GameStatus calculate(Player player, Dealer dealer) {
        if (player.getScore().isGreaterThen(dealer.getScore())) {
            return GameStatus.WIN;
        }
        if (player.getScore().isEqualTo(dealer.getScore())) {
            return GameStatus.DRAW;
        }
        return GameStatus.LOSE;
    }
}
