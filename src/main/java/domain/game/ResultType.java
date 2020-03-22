package domain.game;

import domain.player.Player;
import domain.player.User;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum ResultType {
    BLACKJACK_WIN("1.5", (Player mainPlayer, Player opponentPlayer) -> (
            mainPlayer instanceof User
                    && mainPlayer.isWin(opponentPlayer)
                    && mainPlayer.isBlackJack()
                    && !opponentPlayer.isBlackJack())
    ),
    WIN("1.0", (Player mainPlayer, Player opponentPlayer) -> (
            mainPlayer.isWin(opponentPlayer)
    )),
    DRAW("0", (Player mainPlayer, Player opponentPlayer) -> (
            (mainPlayer.isWin(opponentPlayer)
                    && mainPlayer.isBlackJack()
                    && opponentPlayer.isBlackJack())
                    || (mainPlayer.isDraw(opponentPlayer))
    )),
    BLACKJACK_LOSE("-1.5", (Player mainPlayer, Player opponentPlayer) -> (
            opponentPlayer instanceof User
                    && opponentPlayer.isBlackJack()
                    && !(mainPlayer.isWin(opponentPlayer)
            ))),
    LOSE("-1.0", (Player mainPlayer, Player opponentPlayer) -> (
            !(mainPlayer.isWin(opponentPlayer))
    ));

    private final String rewardRate;
    private final BiPredicate<Player, Player> condition;

    ResultType(String rewardRate, BiPredicate<Player, Player> condition) {
        this.rewardRate = rewardRate;
        this.condition = condition;
    }

    public static ResultType of(Player mainPlayer, Player opponentPlayer) {
        return Arrays.stream(ResultType.values())
                .filter((o) -> o.condition.test(mainPlayer, opponentPlayer))
                .findFirst()
                .orElse(LOSE);
    }

    public String getRewardRate() {
        return rewardRate;
    }
}
