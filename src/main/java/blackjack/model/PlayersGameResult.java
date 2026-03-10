package blackjack.model;

import blackjack.model.user.Player;
import java.util.Map;

public record PlayersGameResult(Map<Player, GameResult> result) {
}
