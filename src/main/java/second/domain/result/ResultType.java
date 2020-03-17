package second.domain.result;

import second.domain.player.Gamer;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum ResultType {
    WIN("승", ((player, player2) -> player2.isBust() || player.isLargerScoreThan(player2))),
    LOSE("패", ((player, player2) -> player.isBust() || player2.isLargerScoreThan(player)));

    private final String name;
    private final BiPredicate<Gamer, Gamer> resultJudge;

    ResultType(final String name, final BiPredicate<Gamer, Gamer> resultJudge) {
        this.name = name;
        this.resultJudge = resultJudge;
    }

    public static ResultType from(final Gamer result, final Gamer compared) {
        return Arrays.stream(ResultType.values())
                .filter(type -> type.resultJudge.test(result, compared))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    public String getName() {
        return name;
    }
}