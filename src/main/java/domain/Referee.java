package domain;

import domain.player.Dealer;
import domain.player.Gambler;

public class Referee {

    private Referee() {
    }

    public static MatchResult judge(Dealer dealer, Gambler gambler) {
        if (gambler.isBust()) {
            return MatchResult.LOSE;
        }
        if (gambler.isBlackJack() && dealer.isBlackJack()) {
            return MatchResult.DRAW;
        }
        if (gambler.isBlackJack()) {
            return MatchResult.BLACKJACK;
        }
        if (dealer.isBlackJack()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBust()) {
            return MatchResult.WIN;
        }

        return compareScore(dealer.score(), gambler.score());
    }

    private static MatchResult compareScore(int dealerScore, int gamblerScore) {
        if (dealerScore > gamblerScore) {
            return MatchResult.LOSE;
        }
        if (dealerScore < gamblerScore) {
            return MatchResult.WIN;
        }
        return MatchResult.DRAW;
    }
}
