package domain;

import domain.participant.Dealer;
import domain.participant.Player;

public enum MatchResult {

    WIN,
    DRAW,
    LOSE;

    public static MatchResult reverse(MatchResult matchResult) {
        if (matchResult == WIN) return LOSE;
        if (matchResult == LOSE) return WIN;

        return DRAW;
    }

    public static MatchResult determineMatchResult(Player player, Dealer dealer) {
        if (player.isBust()) return LOSE;
        if (dealer.isBust()) return WIN;
        if (player.isHigherThan(dealer)) return WIN;

        if (player.isTie(dealer)) {
            if (player.isBlackJack() && !dealer.isBlackJack()) return WIN;
            if (!player.isBlackJack() && dealer.isBlackJack()) return LOSE;
            return DRAW;
        }

        return LOSE;
    }
}
