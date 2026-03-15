package blackjack.dto;

import blackjack.domain.MatchResult;

public record PlayerGameResult(
    String nickname,
    MatchResult matchResult,
    long profit
) {

    public static PlayerGameResult of(String nickname, MatchResult matchResult, long profit) {
        return new PlayerGameResult(nickname, matchResult, profit);
    }
}
