package blackjack.dto;

import blackjack.domain.MatchResult;

public record PlayerGameResult(
    String nickname,
    MatchResult matchResult,
    long profit
) {

}
