package second.domain.result;

import second.domain.gamer.Dealer;
import second.domain.gamer.Gamer;
import second.domain.gamer.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum ResultType {
    ONLY_PLAYER_BLACK_JACK("플레이어 블랙잭", ((player, dealer) -> player.isBlackJack() && !dealer.isBlackJack())),
    BOTH_BLACK_JACK("딜러 플레이어 모두 블랙잭", ((player, dealer) -> player.isBlackJack() && dealer.isBlackJack())),
    WIN("승", ((player, dealer) -> (!player.isBust() && dealer.isBust()) || player.isLargerScoreThan(dealer))),
    LOSE("패", ((player, dealer) -> player.isBust() || dealer.isLargerScoreThan(player)));

    private final String name;
    private final BiPredicate<Gamer, Gamer> resultJudge;

    ResultType(final String name, final BiPredicate<Gamer, Gamer> resultJudge) {
        this.name = name;
        this.resultJudge = resultJudge;
    }

    public static ResultType from(final Player result, final Dealer compared) {
        return Arrays.stream(ResultType.values())
                .filter(type -> type.resultJudge.test(result, compared))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    public String getName() {
        return name;
    }
}