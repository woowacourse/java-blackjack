package blackjack.domain.result;

import blackjack.domain.Status;
import blackjack.domain.player.Hand;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum RatioMather {
    BLACKJACK(1.5, RatioMather::isBlackjack),
    WIN(1.0, RatioMather::isWin),
    TIE(0, RatioMather::isTie),
    LOSE(-1.0, RatioMather::isLose);

    private final double ratio;
    private final BiPredicate<Hand, Hand> judgeResult;

    RatioMather(double ratio, BiPredicate<Hand, Hand> judgeResult) {
        this.ratio = ratio;
        this.judgeResult = judgeResult;
    }

    private static RatioMather findProfit(Hand hand1, Hand hand2) {
        return Arrays.stream(RatioMather.values())
                .filter(ratioMather -> ratioMather.judgeResult.test(hand1, hand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾을수 없는 값입니다."));
    }

    public static boolean isWin(Hand hand1, Hand hand2) {
        return (hand1.status() == Status.BLACKJACK && hand2.status() != Status.BLACKJACK)
                || (hand1.status().isNone() && hand2.status().isBust())
                || ((hand1.status().isNone() && hand2.status().isNone()) && (hand1.getTotalScore() > hand2.getTotalScore()));
    }

    public static boolean isTie(Hand hand1, Hand hand2) {
        return (hand1.status() == Status.BLACKJACK && hand2.status() == Status.BLACKJACK)
                || (hand1.status().isBust() && hand2.status().isBust())
                || ((hand1.status().isNone() && hand2.status().isNone()) && (hand1.getTotalScore() == hand2.getTotalScore()));
    }

    public static boolean isLose(Hand cards1, Hand cards2) {
        return isWin(cards2, cards1);
    }

    private static boolean isBlackjack(Hand cards1, Hand cards2) {
        return cards1.status().isBlackjack()
                && !cards2.status().isBlackjack();
    }

    public double getRatio() {
        return ratio;
    }
}
