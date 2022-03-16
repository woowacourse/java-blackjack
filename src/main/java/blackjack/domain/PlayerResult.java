package blackjack.domain;

import blackjack.domain.User.Dealer;
import blackjack.domain.User.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum PlayerResult {
    BLACKJACK("블랙잭", (dealer, player) -> player.isBlackJack() && !dealer.isBlackJack()),
    WIN("승", (dealer, player) -> dealer.isBust() || (player.isGreaterScoreThan(dealer) && !player.isBust())),
    DRAW("무", (dealer, player) -> isAllBlackJack(dealer, player) || dealer.isSameScoreWithNotBlackJack(player)),
    LOSE("패", (dealer, player) -> player.isBust() || (dealer.isGreaterScoreThan(player) && !dealer.isBust())),
    ;

    private final String value;
    private final BiPredicate<Dealer, Player> biPredicate;

    PlayerResult(String value, BiPredicate<Dealer, Player> biPredicate) {
        this.value = value;
        this.biPredicate = biPredicate;
    }

    public static PlayerResult valueOf(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(playerResult -> playerResult.biPredicate.test(dealer, player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 결과를 찾을 수 없습니다."));
    }

    private static boolean isAllBlackJack(Dealer dealer, Player player) {
        return dealer.isBlackJack() && player.isBlackJack();
    }

}
