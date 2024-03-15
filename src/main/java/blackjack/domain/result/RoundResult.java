package blackjack.domain.result;

import blackjack.domain.participant.Player;
import java.util.Map;

public record RoundResult(Map<Player, HandResult> playersResult) {
}
