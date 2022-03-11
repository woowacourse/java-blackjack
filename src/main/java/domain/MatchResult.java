package domain;

import domain.player.Dealer;
import domain.player.Gambler;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MatchResult {
    WIN("승", (dealer, gambler) ->
            !gambler.isBust() && (dealer.isBust() || dealer.getPlayResult() < gambler.getPlayResult())),
    LOSE("패", (dealer, gambler) -> gambler.isBust() || (dealer.getPlayResult() > gambler.getPlayResult())),
    DRAW("무", (dealer, gambler) -> !gambler.isBust() && dealer.getPlayResult() == gambler.getPlayResult()),
    NO_MATCH("판정오류", (dealer, gambler) -> false);

    private final BiPredicate<Dealer, Gambler> judge;
    private final String result;

    MatchResult(String result, BiPredicate<Dealer, Gambler> judge) {
        this.result = result;
        this.judge = judge;
    }

    public static MatchResult of(Dealer dealer, Gambler gambler) {
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
