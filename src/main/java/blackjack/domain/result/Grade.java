package blackjack.domain.result;

import blackjack.domain.game.Dealer;
import blackjack.domain.game.Player;

public enum Grade {

    WIN("승"),
    TIE("무"),
    LOSE("패"),
    PROCEED("진행")
    ;

    private final String grade;

    Grade(final String grade) {
        this.grade = grade;
    }

    public static Grade grade(final Dealer dealer, final Player player) {
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return TIE;
        }
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return LOSE;
        }
        if (!dealer.isBlackjack() && player.isBlackjack()) {
            return WIN;
        }
        return PROCEED;
    }
}
