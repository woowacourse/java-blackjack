package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.player.TotalScore;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public enum RatioMather {
    BLACKJACK(1.5, RatioMather::isBlackjack),
    WIN(1.0, RatioMather::isWin),
    TIE(0, RatioMather::isTie),
    LOSE(-1.0, RatioMather::isLose);

    private final double ratio;
    private final BiPredicate<List<Card>, List<Card>> judgeResult;

    RatioMather(double ratio, BiPredicate<List<Card>, List<Card>> judgeResult) {
        this.ratio = ratio;
        this.judgeResult = judgeResult;
    }

    public static RatioMather of(List<Card> playerCards, List<Card> dealerCards) {
        return Arrays.stream(RatioMather.values())
                .filter(ratioMather -> ratioMather.judgeResult.test(playerCards, dealerCards))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾을수 없는 값입니다."));
    }

    private static boolean isWin(List<Card> playerCards, List<Card> dealerCards) {
        if (Status.of(playerCards) == Status.BLACKJACK && Status.of(dealerCards) != Status.BLACKJACK) {
            return true;
        }
        if (Status.of(playerCards) == Status.NONE && Status.of(dealerCards) == Status.BUST) {
            return true;
        }
        if (Status.of(playerCards) == Status.NONE && Status.of(dealerCards) == Status.NONE) {
            return isPlayerCardScoreHigher(playerCards, dealerCards);
        }
        return false;
    }

    private static boolean isPlayerCardScoreHigher(List<Card> playerCards, List<Card> dealerCards) {
        return calculateTotalScore(playerCards) > calculateTotalScore(dealerCards);
    }

    private static int calculateTotalScore(List<Card> cards) {
        return TotalScore.calculateTotalScore(cards).getTotalScore();
    }

    private static boolean isTie(List<Card> playerCards, List<Card> dealerCards) {
        if (Status.of(playerCards) == Status.BLACKJACK && Status.of(dealerCards) == Status.BLACKJACK) {
            return true;
        }
        if (Status.of(playerCards) == Status.BUST && Status.of(dealerCards) == Status.BUST) {
            return true;
        }
        if (Status.of(playerCards) == Status.NONE && Status.of(dealerCards) == Status.BUST) {
            return true;
        }
        if (Status.of(playerCards) == Status.NONE && Status.of(dealerCards) == Status.NONE) {
            return isSameScore(playerCards, dealerCards);
        }
        return false;
    }

    private static boolean isSameScore(List<Card> playerCards, List<Card> dealerCards) {
        return calculateTotalScore(playerCards) == calculateTotalScore(dealerCards);
    }

    private static boolean isLose(List<Card> playerCards, List<Card> dealerCards) {
        return isWin(dealerCards, playerCards);
    }

    private static boolean isBlackjack(List<Card> playerCards, List<Card> dealerCards) {
        return Status.of(playerCards) == Status.BLACKJACK
                && Status.of(dealerCards) == Status.BLACKJACK;
    }

    public double getRatio() {
        return ratio;
    }
}
