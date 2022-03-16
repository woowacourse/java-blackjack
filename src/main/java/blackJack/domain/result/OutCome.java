package blackJack.domain.result;

import blackJack.domain.card.Cards;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum OutCome {
    WIN("승", (dealer, player) ->
            (!dealer.isBlackJack() && player.isBlackJack()) ||
                    (!player.isBust() && dealer.isBust()) ||
                    (!player.isBust() && (dealer.addScore() < player.addScore()))
    ),
    DRAW("무", (dealer, player) ->
            (dealer.isBlackJack() && player.isBlackJack()) ||
                    (!dealer.isBlackJack() && !player.isBlackJack() && dealer.addScore() == player.addScore())
    ),
    LOSE("패", (dealer, player) ->
            (dealer.isBlackJack() && !player.isBlackJack()) ||
                    (dealer.isBust() && player.isBust()) ||
                    (!dealer.isBust() && player.isBust()) ||
                    (!dealer.isBust() && (dealer.addScore() > player.addScore()))
    );

    private final String result;
    private final BiPredicate<Cards, Cards> predicate;

    OutCome(String result, BiPredicate<Cards, Cards> predicate) {
        this.result = result;
        this.predicate = predicate;
    }

    public static OutCome of(Cards cards, Cards otherCards) {
        return Arrays.stream(values())
                .filter(result -> result.predicate.test(cards, otherCards))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));
    }

    public String getResult() {
        return result;
    }
}
