package blackjack.model;

import java.util.Map;

public record PlayersGameResult(Map<Player, GameResult> result) {
}
