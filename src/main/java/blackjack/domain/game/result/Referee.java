package blackjack.domain.game.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;

public class Referee {
    private static final List<GameResult> allResults = List.of(
            PlayerBlackjack.getInstance(),
            PlayerLose.getInstance(),
            Push.getInstance(),
            PlayerWin.getInstance()
    );

    public static GameResult getGameResult(Dealer dealer, Player player) {
        return allResults.stream()
                .filter(result -> result.isSatisfy(dealer, player))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));
    }
}
