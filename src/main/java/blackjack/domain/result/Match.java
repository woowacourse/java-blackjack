package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiFunction;

import blackjack.domain.player.Player;

public enum Match {

    WIN_BLACKJACK("블랙잭_승", Match::isBlackjackWin),
    WIN("승", Match::isWin),
    LOSE_BLACKJACK("블랙잭_패", Match::isBlackjackLose),
    LOSE("패", Match::isLose),
    DRAW("무", Match::isDraw)
    ;

    private final String result;
    private final BiFunction<Player, Player, Boolean> expression;

    Match(String result, BiFunction<Player, Player, Boolean> expression) {
        this.result = result;
        this.expression = expression;
    }

    public static Match findWinner(Player player, Player competitor) {
        if (!player.isDealer()) {
            return Arrays.stream(Match.values())
                    .filter(match -> match.expression.apply(player, competitor))
                    .findFirst()
                    .orElseThrow();
        }
        return Arrays.stream(Match.values())
                .filter(match -> match.expression.apply(competitor, player))
                .findFirst()
                .orElseThrow();
    }

    private static boolean isBlackjackWin(Player player, Player competitor) {
        return player.isBlackJack(competitor);
    }

    private static boolean isBlackjackLose(Player player, Player competitor) {
        return competitor.isBlackJack(player);
    }

    private static boolean isWin(Player player, Player competitor) {
        return player.isWin(competitor);
    }

    private static boolean isLose(Player player, Player competitor) {
        return competitor.isWin(player);
    }

    private static Boolean isDraw(Player player, Player competitor) {
        return player.isDraw(competitor);
    }

    public boolean isMatchBlackjackWin() {
        return this == WIN_BLACKJACK;
    }

    public boolean isMatchWin() {
        return this == WIN;
    }

    public boolean isMatchBlackjackLose() {
        return this == LOSE_BLACKJACK;
    }

    public boolean isMatchLose() {
        return this == LOSE;
    }
}
