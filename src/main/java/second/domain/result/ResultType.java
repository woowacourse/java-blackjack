package second.domain.result;

import second.domain.player.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum ResultType {
    WIN("승", ((player, player2) -> player.isLargerScoreThan(player2))),
    LOSE("패", ((player, player2) -> !player.isLargerScoreThan(player2)));

    private final String name;
    private final BiPredicate<Player, Player> resultJudge;

    ResultType(String name, BiPredicate<Player, Player> resultJudge) {
        this.name = name;
        this.resultJudge = resultJudge;
    }

    public static ResultType from(Player result, Player compared) {
        return Arrays.stream(ResultType.values())
                .filter(type -> type.resultJudge.test(result, compared))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    public String getName() {
        return name;
    }
}