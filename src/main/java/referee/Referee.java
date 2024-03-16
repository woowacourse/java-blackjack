package referee;

import participant.dealer.Dealer;
import gameResult.GameResult;
import gameResult.PlayerBlackJack;
import gameResult.PlayerBust;
import gameResult.PlayerLose;
import gameResult.PlayerPush;
import gameResult.PlayerWin;
import java.util.List;
import participant.player.Player;

public class Referee {

    private final List<GameResult> orderedGameResults;

    public Referee() {
        this.orderedGameResults = List.of(new PlayerBust(), new PlayerPush(), new PlayerBlackJack(), new PlayerWin(),
                new PlayerLose());
    }

    public GameResult judge(Player player, Dealer dealer) {
        return orderedGameResults.stream()
                .filter(result -> result.isCorrespondent(player, dealer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 상태가 없습니다."));
    }
}
