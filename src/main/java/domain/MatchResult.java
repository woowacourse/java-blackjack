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

    public static MatchResult determineMatchResultWithDealer(Player player, Dealer dealer) {
        if (player.isBust()) return MatchResult.LOSE;
        if (dealer.isBust()) return MatchResult.WIN;
        if (player.getScore() > dealer.getScore()) return MatchResult.WIN;

        if (player.getScore() == dealer.getScore()) {
            if (player.isBlackJack() && !dealer.isBlackJack()) return MatchResult.WIN;
            if (!player.isBlackJack() && dealer.isBlackJack()) return MatchResult.LOSE;
            return MatchResult.DRAW;
        }

        return MatchResult.LOSE;
    }
}
