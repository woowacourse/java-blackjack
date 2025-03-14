package result.handStatus;

import participant.Dealer;
import participant.Player;
import result.GameStatus;

public class ScoreComparison implements HandStatus {
    @Override
    public GameStatus calculateResult(Player player, Dealer dealer) {
        if (player.getScore().isGreaterThen(dealer.getScore())) {
            return GameStatus.WIN;
        }
        if (player.getScore().isEqualTo(dealer.getScore())) {
            return GameStatus.DRAW;
        }
        return GameStatus.LOSE;
    }
}
