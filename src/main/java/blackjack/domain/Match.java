package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Match {
    WIN("승", "패", Match::winPlayerCondition),
    LOSE("패", "승", Match::losePlayerCondition),
    DRAW("무", "무", Match::isDraw),
    ;

    public static final int MAX_WINNER_POINT = 21;

    private final String result;
    private final String oppositeResult;
    private final BiFunction<Player, Player, Boolean> expression;

    Match(String result, String oppositeResult, BiFunction<Player, Player, Boolean> expression) {
        this.result = result;
        this.oppositeResult = oppositeResult;
        this.expression = expression;
    }

    public static Match findWinner(Player guest, Player dealer) {
        return Arrays.stream(Match.values())
                .filter(match -> match.expression.apply(guest, dealer))
                .findFirst()
                .orElseThrow();
    }

    public String getResult() {
        return result;
    }

    public Match getDealerResult() {
        return Arrays.stream(values())
                .filter(this::findOppositeResult)
                .findFirst()
                .orElseThrow();
    }

    private boolean findOppositeResult(Match match) {
        return match.result.equals(this.oppositeResult);
    }

    private static boolean winPlayerCondition(Player guest, Player dealer) {
        return guest.isWin(guest, dealer);
    }

    private static boolean losePlayerCondition(Player guest, Player dealer) {
        return dealer.isWin(guest, dealer);
    }

    private static Boolean isDraw(Player guest, Player dealer) {
        return guest.isDraw(dealer);
    }
}
