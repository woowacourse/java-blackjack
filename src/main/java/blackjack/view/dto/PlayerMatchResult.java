package blackjack.view.dto;

import blackjack.model.referee.MatchResult;

public record PlayerMatchResult(String name, MatchResult matchResult) {
}
