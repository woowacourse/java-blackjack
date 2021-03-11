package blackjack.domain.result;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public enum ResultType {
    WIN("승", (dealer, gamer) -> {
        if (!dealer.isBlackjack() && gamer.isBlackjack()) {
            return true;
        }
        if (dealer.isBust()) {
            return true;
        }
        return !gamer.isBust() && dealer.calculateScore() < gamer.calculateScore();
    }),
    DRAW("무", (dealer, gamer) -> {
        if (dealer.isBlackjack() && gamer.isBlackjack()) {
            return true;
        }
        return dealer.calculateScore() == gamer.calculateScore();
    }),
    LOSE("패", (dealer, gamer) -> {
        if (dealer.isBlackjack() && !gamer.isBlackjack()) {
            return true;
        }
        if (gamer.isBust()) {
            return true;
        }
        return dealer.calculateScore() > gamer.calculateScore();
    });

    private final String value;
    private final BiFunction<Dealer, Gamer, Boolean> matcher;

    ResultType(String value,
        BiFunction<Dealer, Gamer, Boolean> matcher) {
        this.value = value;
        this.matcher = matcher;
    }

    public static ResultType judgeGameResult(Dealer dealer, Gamer gamer) {
        return Arrays.stream(values())
            .filter(resultType -> resultType.matcher.apply(dealer, gamer))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 승패를 판단 할 수 없습니다."));
    }

    public String getValue() {
        return value;
    }

    public ResultType reverse() {
        if (this == ResultType.WIN) {
            return ResultType.LOSE;
        }
        if (this == ResultType.LOSE) {
            return ResultType.WIN;
        }
        return ResultType.DRAW;
    }
}
