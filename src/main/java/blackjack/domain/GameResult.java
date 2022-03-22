package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

public enum GameResult {
    BLACKJACK(1.5, (dealer, gamer) -> gamer.isBlackJack() && !dealer.isBlackJack()),

    WIN(1.0, (dealer, gamer) -> !gamer.isBust()
        && (dealer.calculateResult() < gamer.calculateResult() || dealer.isBust())
    ),
    DRAW(0.0, (dealer, gamer) -> !gamer.isBust()
        && dealer.calculateResult() == gamer.calculateResult()
    ),
    LOSE(-1.0, (dealer, gamer) -> gamer.isBust()
        || (dealer.calculateResult() > gamer.calculateResult() && !dealer.isBust())
    );

    private final Double multiplePoint;
    private final BiPredicate<Dealer, Gamer> predicate;

    GameResult(final Double multiplePoint, final BiPredicate<Dealer, Gamer> predicate) {
        this.multiplePoint = multiplePoint;
        this.predicate = predicate;
    }

    private static GameResult findResult(final Dealer dealer, final Gamer gamer) {
        return Arrays.stream(values())
            .filter(result -> result.predicate.test(dealer, gamer))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하는 결과가 없습니다."));
    }

    public static Map<Gamer, Long> calculateGamersProfit(final Dealer dealer,
        final List<Gamer> gamers) {
        Map<Gamer, Long> gamersProfit = new LinkedHashMap<>();
        for (Gamer gamer : gamers) {
            gamersProfit
                .put(gamer,
                    (long) (GameResult.findResult(dealer, gamer).getMultiplePoint() * gamer
                        .getBetMoney()));
        }
        return gamersProfit;
    }

    public static Long calculateDealerProfit(Map<Gamer, Long> gamersProfit) {
        return gamersProfit.values().stream()
            .mapToLong(result -> -result)
            .sum();
    }

    private Double getMultiplePoint() {
        return multiplePoint;
    }
}
