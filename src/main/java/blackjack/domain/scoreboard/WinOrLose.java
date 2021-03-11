package blackjack.domain.scoreboard;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participant;
import blackjack.domain.user.User;

import java.util.Arrays;
import java.util.function.BiPredicate;

import static blackjack.domain.user.status.Status.BURST;

public enum WinOrLose {
    WIN("승"
            , (dealer, user) -> dealer.isSameStatus(BURST) && user.isNotStatus(BURST)
            , Participant::scoreSmallerThan),
    DRAW("무"
            , (dealer, user) -> dealer.isSameStatus(BURST) && user.isSameStatus(BURST)
            , Participant::isSameScore),
    LOSE("패"
            , (dealer, user) -> user.isSameStatus(BURST)
            , Participant::scoreBiggerThan);

    private static final String NONE_MATCH_CONDITION_ERROR_MSG = "승무패 조건에 없는 경우입니다.";

    private final String character;
    private final BiPredicate<Dealer, User> statusCompareCondition;
    private final BiPredicate<Dealer, User> scoreCompareCondition;

    WinOrLose(String character, BiPredicate<Dealer, User> statusCompareCondition, BiPredicate<Dealer, User> scoreCompareCondition) {
        this.character = character;
        this.statusCompareCondition = statusCompareCondition;
        this.scoreCompareCondition = scoreCompareCondition;
    }

    public static WinOrLose decideWinOrLose(User user, Dealer dealer) {
        return Arrays.stream(values())
                .filter(winOrLose -> winOrLose.statusCompareCondition.test(dealer, user))
                .findFirst()
                .orElseGet(() -> decideWinOrLoseByScore(user, dealer));
    }

    private static WinOrLose decideWinOrLoseByScore(User user, Dealer dealer) {
        return Arrays.stream(values())
                .filter(winOrLose -> winOrLose.scoreCompareCondition.test(dealer, user))
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
