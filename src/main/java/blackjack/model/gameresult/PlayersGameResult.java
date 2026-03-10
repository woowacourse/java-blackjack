package blackjack.model.gameresult;

import blackjack.model.user.Player;
import java.util.Map;

public record PlayersGameResult(Map<Player, GameResult> result) {
}
