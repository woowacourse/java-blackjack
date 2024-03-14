package blackjack.domain.game;

import blackjack.domain.game.result.GameResult;
import blackjack.domain.game.result.PlayerBlackjack;
import blackjack.domain.game.result.PlayerLose;
import blackjack.domain.game.result.PlayerWin;
import blackjack.domain.game.result.Push;
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
