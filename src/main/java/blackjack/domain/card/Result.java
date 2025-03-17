package blackjack.domain.card;

import java.util.Map;

public record Result(Map<WinningResult, Integer> resultIntegerMap, double bettingResult) {
}
