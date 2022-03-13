package domain;

import domain.player.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MatchResult {
    WIN("승", (dealer, gambler) ->
            !gambler.isBust() && (dealer.isBust() || dealer.getScore() < gambler.getScore())),
    LOSE("패", (dealer, gambler) -> gambler.isBust() || (dealer.getScore() > gambler.getScore())),
    DRAW("무", (dealer, gambler) -> !gambler.isBust() && dealer.getScore() == gambler.getScore()),
    NO_MATCH("판정오류", (dealer, gambler) -> false);

    private final BiPredicate<Player, Player> judge;
    private final String result;

    MatchResult(String result, BiPredicate<Player, Player> judge) {
        this.result = result;
        this.judge = judge;
    }

    public static MatchResult of(Player dealer, Player gambler) {
        return Arrays.stream(values())
                .filter(matchResult -> matchResult.judge.test(dealer, gambler))
                .findAny()
                .orElse(NO_MATCH);
    }

    public String getResult() {
        return result;
    }

    public static MatchResult opposite(MatchResult matchResult) {
        if (matchResult == WIN) {
            return LOSE;
        }

        if (matchResult == LOSE) {
            return WIN;
        }

        return DRAW;
    }
}
