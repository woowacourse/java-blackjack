package domain;

import domain.player.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MatchResult {
    WIN((dealer, gambler) ->
            !gambler.isBust() && (dealer.isBust() || dealer.getScore() < gambler.getScore())),
    LOSE((dealer, gambler) -> gambler.isBust() || (dealer.getScore() > gambler.getScore())),
    DRAW((dealer, gambler) -> !gambler.isBust() && dealer.getScore() == gambler.getScore()),
    NO_MATCH((dealer, gambler) -> false);

    private final BiPredicate<Player, Player> judge;

    MatchResult(BiPredicate<Player, Player> judge) {
        this.judge = judge;
    }

    public static MatchResult of(Player dealer, Player gambler) {
        return Arrays.stream(values())
                .filter(matchResult -> matchResult.judge.test(dealer, gambler))
                .findAny()
                .orElse(NO_MATCH);
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
