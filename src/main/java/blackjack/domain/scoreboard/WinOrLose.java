package blackjack.domain.scoreboard;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.status.Status;
import blackjack.domain.user.User;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum WinOrLose {
    WIN("승", (dealerScore, userScore) -> dealerScore < userScore),
    DRAW("무", Integer::equals),
    LOSE("패", (dealerScore, userScore) -> dealerScore > userScore);

    private static final String NONE_MATCH_CONDITION_ERROR_MSG = "승무패 조건에 없는 경우입니다.";
    private final String character;
    private final BiPredicate<Integer, Integer> scoreCompareCondition;

    WinOrLose(String character, BiPredicate<Integer, Integer> scoreCompareCondition) {
        this.character = character;
        this.scoreCompareCondition = scoreCompareCondition;
    }

    public static WinOrLose decideWinOrLose(User user, Dealer dealer) {
        if (dealer.isSameStatus(Status.BURST)) {
            return decideWinOrLoseWhenDealerIsBurst(user);
        }

        return decideWinOrLoseWhenDealerIsNotBurst(user, dealer);
    }

    private static WinOrLose decideWinOrLoseWhenDealerIsBurst(User user) {
        if (user.isSameStatus(Status.BURST)) {
            return DRAW;
        }
        return WIN;
    }

    private static WinOrLose decideWinOrLoseWhenDealerIsNotBurst(User user, Dealer dealer) {
        if (user.isSameStatus(Status.BURST)) {
            return LOSE;
        }

        return Arrays.stream(values())
                .filter(winOrLose -> winOrLose.scoreCompareCondition.test(dealer.calculateScore(), user.calculateScore()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NONE_MATCH_CONDITION_ERROR_MSG));
    }

    public static WinOrLose opposite(WinOrLose winOrLose) {
        if (winOrLose == WIN) {
            return LOSE;
        }
        if (winOrLose == LOSE) {
            return WIN;
        }
        return winOrLose;
    }

    public String getCharacter() {
        return character;
    }
}
