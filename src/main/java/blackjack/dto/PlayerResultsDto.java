package blackjack.dto;

import java.util.Map;

import blackjack.domain.gamer.GameResult;
import blackjack.domain.gamer.Name;

public record PlayerResultsDto(Map<Name, GameResult> resultMap) {
}
