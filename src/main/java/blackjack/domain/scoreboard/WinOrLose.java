package blackjack.domain.scoreboard;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.User;

import java.util.Arrays;
import java.util.function.BiPredicate;

import static blackjack.domain.user.Status.BURST;

public enum WinOrLose {
    WIN("승"
            , (dealer, user) -> dealer.isSameStatus(BURST) && user.isNotStatus(BURST)
            , (dealerScore, userScore) -> dealerScore < userScore),
    DRAW("무"
            , (dealer, user) -> dealer.isSameStatus(BURST) && user.isSameStatus(BURST)
            , Integer::equals),
    LOSE("패"
            , (dealer, user) -> dealer.isNotStatus(BURST) && user.isSameStatus(BURST)
            , (dealerScore, userScore) -> dealerScore > userScore);


    private final String character;
    private final BiPredicate<User, Dealer> findByStatus;
    private final BiPredicate<Integer, Integer> findByScore;

    WinOrLose(String character, BiPredicate<User, Dealer> findByStatus, BiPredicate<Integer, Integer> findByScore) {
        this.character = character;
        this.findByStatus = findByStatus;
        this.findByScore = findByScore;
    }

    public static WinOrLose decideWinOrLose(User user, Dealer dealer) {
        return Arrays.stream(values())
                .filter(winOrLose -> winOrLose.findByStatus.test(user, dealer))
                .findFirst()
                .orElseGet(() -> decideWinOrLoseByScore(user, dealer));
    }

    private static WinOrLose decideWinOrLoseByScore(User user, Dealer dealer) {
        int dealerScore = dealer.calculateScore();
        int userScore = user.calculateScore();

        return Arrays.stream(values())
                .filter(winOrLose -> winOrLose.findByScore.test(dealerScore, userScore))
                .findFirst().get();
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