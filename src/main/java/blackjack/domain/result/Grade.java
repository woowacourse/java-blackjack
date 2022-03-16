package blackjack.domain.result;

import blackjack.domain.game.Dealer;
import blackjack.domain.game.Player;

import java.util.Arrays;

public enum Grade {

    BLACKJACK_WIN(1.5),
    WIN(1),
    TIE(0),
    LOSE(-1),
    PROCEED(0)
    ;

    private final double rate;

    Grade(final double rate) {
        this.rate = rate;
    }

    public static Grade gradeToInitCards(final Dealer dealer, final Player player) {
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return TIE;
        }
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return LOSE;
        }
        if (!dealer.isBlackjack() && player.isBlackjack()) {
            return BLACKJACK_WIN;
        }
        return PROCEED;
    }

    public static Grade grade(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust() || dealer.isLowerScore(player)) {
            return WIN;
        }
        if (dealer.isHigherScore(player)) {
            return LOSE;
        }
        return TIE;
    }

    public static int rate(final Grade grade, final int betting) {
        return Arrays.stream(Grade.values())
                .filter(nowGrade -> nowGrade.equals(grade))
                .map(nowGrade -> (int) (betting * nowGrade.rate))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Grade 입니다."));
    }
}
