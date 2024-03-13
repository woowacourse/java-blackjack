package blackjack.view.dto;

import blackjack.model.player.MatchResult;

public record PlayerMatchResultOutcome(String name, MatchResult matchResult) {
}
