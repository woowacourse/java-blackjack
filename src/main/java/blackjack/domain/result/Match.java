package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiFunction;

import blackjack.domain.player.Player;

public enum Match {

    WIN("승", "패", Match::winPlayerCondition),
    LOSE("패", "승", Match::losePlayerCondition),
    DRAW("무", "무", Match::isDraw),
    ;

    private final String result;
    private final String oppositeResult;
    private final BiFunction<Player, Player, Boolean> expression;

    Match(String result, String oppositeResult, BiFunction<Player, Player, Boolean> expression) {
        this.result = result;
        this.oppositeResult = oppositeResult;
        this.expression = expression;
    }

    public static Match findWinner(Player player, Player competitor) {
        return Arrays.stream(Match.values())
                .filter(match -> match.expression.apply(player, competitor))
                .findFirst()
                .orElseThrow();
    }

    private boolean findOppositeResult(Match match) {
        return match.result.equals(this.oppositeResult);
    }

    private static boolean winPlayerCondition(Player player, Player competitor) {
        return player.isWin(competitor);
    }

    private static boolean losePlayerCondition(Player player, Player competitor) {
        return competitor.isWin(player);
    }

    private static Boolean isDraw(Player player, Player competitor) {
        return player.isDraw(competitor);
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
}
