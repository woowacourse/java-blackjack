package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum ResultType {
    WIN("승", 1,(dealer, gamer) -> {
        if (!dealer.isBlackjack() && gamer.isBlackjack()) {
            return true;
        }
        if (dealer.isBust()) {
            return true;
        }
        return !gamer.isBust() && dealer.calculateScore() < gamer.calculateScore();
    }),
    DRAW("무", 0, (dealer, gamer) -> {
        if (dealer.isBlackjack() && gamer.isBlackjack()) {
            return true;
        }
        return dealer.calculateScore() == gamer.calculateScore();
    }),
    LOSE("패", -1, (dealer, gamer) -> {
        if (dealer.isBlackjack() && !gamer.isBlackjack()) {
            return true;
        }
        if (gamer.isBust()) {
            return true;
        }
        return dealer.calculateScore() > gamer.calculateScore();
    });

    private final String value;
    private final int profit;
    private final BiFunction<Dealer, Gamer, Boolean> matcher;

    ResultType(String value, int profit, BiFunction<Dealer, Gamer, Boolean> matcher) {
        this.value = value;
        this.profit = profit;
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

    public int getProfit() {
        return profit;
    }

    public ResultType reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
