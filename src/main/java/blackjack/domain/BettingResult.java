package blackjack.domain;

import blackjack.domain.participant.BettingMoney;
import blackjack.domain.participant.Participant;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;

public enum BettingResult {

    WIN_BY_BLACKJACK(BettingResult::isWinByBlackjack, bettingMoney -> bettingMoney.times(1.5)),
    WIN(BettingResult::isWin, bettingMoney -> bettingMoney),
    TIE(BettingResult::isTie, bettingMoney -> bettingMoney.times(0)),
    LOSE(BettingResult::isLose, bettingMoney -> bettingMoney.times(-1));

    private final BiPredicate<Participant, Participant> condition;
    private final UnaryOperator<BettingMoney> operator;

    BettingResult(BiPredicate<Participant, Participant> condition, UnaryOperator<BettingMoney> operator) {
        this.condition = condition;
        this.operator = operator;
    }

    public static BettingResult of(Participant player, Participant dealer) {
        return Arrays.stream(values())
                .filter(bettingResult -> bettingResult.condition.test(player, dealer))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("결과가 정상적으로 나오지 않았습니다."));
    }

    private static boolean isWinByBlackjack(Participant player, Participant dealer) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    private static boolean isWin(Participant player, Participant dealer) {
        return (!player.isBust() && dealer.isBust()) ||
                (!player.isBust() && player.getTotalScore() > dealer.getTotalScore());
    }

    private static boolean isTie(Participant player, Participant dealer) {
        return !player.isBust() && (player.getTotalScore() == dealer.getTotalScore());
    }

    private static boolean isLose(Participant player, Participant dealer) {
        return player.isBust() || (player.getTotalScore() < dealer.getTotalScore());
    }

    public int getResult(BettingMoney bettingMoney) {
        return operator.apply(bettingMoney).getAmount();
    }
}
