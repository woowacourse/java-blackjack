package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum CompareResult {

    BLACK_JACK(new BlackJack(), (dealerResult, gamer) -> isBlackJack(gamer)),
    WIN(new Keep(), (dealerResult, gamer) -> isWin(dealerResult, gamer.calculateResult())),
    DRAW(new Draw(), (dealerResult, gamer) -> isDraw(dealerResult, gamer.calculateResult())),
    LOSE(new Lose(), (dealerResult, gamer) -> isLose(dealerResult, gamer.calculateResult()));

    private final ResultStrategy resultStrategy;

    private final BiPredicate<Integer, Gamer> predicate;

    CompareResult(final ResultStrategy resultStrategy, final BiPredicate<Integer, Gamer> predicate) {
        this.resultStrategy = resultStrategy;
        this.predicate = predicate;
    }

    public static CompareResult findCompareResult(final int dealerResult, final Gamer gamer) {
        return Arrays.stream(values())
                .filter((result) -> result.predicate.test(dealerResult, gamer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하는 결과가 없습니다."));
    }

    private static boolean isBlackJack(final Player player) {
        return player.showCards().size() == Card.START_CARD_COUNT &&
                player.calculateResult() == Gamer.LIMIT_GAMER_TOTAL_POINT;
    }

    private static boolean isWin(final Integer dealerResult, final Integer gamerResult) {
        return (dealerResult > Gamer.LIMIT_GAMER_TOTAL_POINT &&
                gamerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT)
                || (dealerResult < gamerResult &&
                gamerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT);
    }

    private static boolean isDraw(final Integer dealerResult, final Integer gamerResult) {
        return dealerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT &&
                gamerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT &&
                dealerResult == gamerResult;
    }

    private static boolean isLose(final Integer dealerResult, final Integer gamerResult) {
        return dealerResult > gamerResult ||
                gamerResult > Gamer.LIMIT_GAMER_TOTAL_POINT;
    }

    public ResultStrategy getResult() {
        return resultStrategy;
    }
}
