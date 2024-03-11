package domain.blackjack;

import domain.player.Player;
import java.util.Map;

public record GameResult(Map<Player, OneOnOneResult> gameResult) {
}
