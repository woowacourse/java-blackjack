package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiFunction;

import blackjack.domain.player.Player;

public enum Match {

    WIN_BLACKJACK("블랙잭_승", Match::isBlackjack),
    WIN("승", Match::isWin),
    LOSE_BLACKJACK("블랙잭_패", Match::isBlackjackLose),
    LOSE("패", Match::isLose),
    ;

    private final String result;
    private final BiFunction<Player, Player, Boolean> expression;

    Match(String result, BiFunction<Player, Player, Boolean> expression) {
        this.result = result;
        this.expression = expression;
    }

    public static Match findWinner(Player player, Player competitor) {
        return Arrays.stream(Match.values())
                .filter(match -> match.expression.apply(player, competitor))
                .findFirst()
                .orElseThrow();
    }

    private static boolean isBlackjack(Player player, Player competitor) {
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

    public boolean isMatchBlackjackLose() {
        return this.result == LOSE_BLACKJACK.result;
    }

    public boolean isMatchLose() {
        return this.result == LOSE.result;
    }

    public boolean isMatchBlackjackWin() {
        return this.result == WIN_BLACKJACK.result;
    }

    public boolean isMatchWin() {
        return this.result == WIN.result;
    }
}
