package domain;

import domain.player.Dealer;
import domain.player.Gambler;

public enum MatchResult {
    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private static final int BLACKJACK_MAX_LIMIT = 21;

    private final double rate;

    MatchResult(double rate) {
        this.rate = rate;
    }

    public static MatchResult of(Gambler gambler, Dealer dealer) {
        if (isBothBlackJack(gambler, dealer)) {
            return DRAW;
        }
        if (gambler.isBlackJack()) {
            return BLACKJACK;
        }
        if (dealer.isBlackJack()) {
            return LOSE;
        }
        return compareScore(gambler.score(), dealer.score());
    }

    private static boolean isBothBlackJack(Gambler gambler, Dealer dealer) {
        return gambler.isBlackJack() && dealer.isBlackJack();
    }


    private static MatchResult compareScore(int gamblerScore, int dealerScore) {
        if (isBust(gamblerScore)) {
            return LOSE;
        }

        if (isBust(dealerScore)) {
            return WIN;
        }

        return compareNormalScore(gamblerScore, dealerScore);
    }

    private static boolean isBust(int score) {
        return score > BLACKJACK_MAX_LIMIT;
    }

    private static MatchResult compareNormalScore(int gamblerScore, int dealerScore) {
        if (gamblerScore > dealerScore) {
            return WIN;
        }

        if (gamblerScore < dealerScore) {
            return LOSE;
        }

        return DRAW;
    }

    public double getRate() {
        return rate;
    }
}
