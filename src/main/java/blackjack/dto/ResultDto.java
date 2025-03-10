package blackjack.dto;

import blackjack.domain.GameResultType;
import blackjack.domain.Player;
import java.util.Map;

public record ResultDto(Map<Player, GameResultType> playersResult, Map<GameResultType, Integer> dealerResult) {
}
