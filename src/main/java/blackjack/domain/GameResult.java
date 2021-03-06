package blackjack.domain;

import blackjack.domain.participants.Participant;
import java.util.List;

public class GameResult {

    public int calDealerWinCount(final Participant dealer, final List<Participant> players) {
        return (int) players.stream()
            .filter(dealer::isWinner)
            .count();
    }

    public Result decideWinner(final Participant player, final Participant dealer) {
        boolean isPlayerWin = player.isWinner(dealer);
        if (isPlayerWin) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

}
