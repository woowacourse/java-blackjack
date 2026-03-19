package domain.result;

import domain.participant.Participant;

public enum Outcome {
    BLACKJACK_WIN("블랙잭 승", 1.5),
    WIN("승", 1.0),
    DRAW("무", 1.0),
    LOSE("패", -1.0);

    private final String name;
    private final double rate;

    Outcome(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public static Outcome decideWinner(Participant player, Participant dealer) {
        if (player.isFirstBlackJack() && dealer.isFirstBlackJack()) {
            return DRAW;
        }
        if (player.isFirstBlackJack()) {
            return BLACKJACK_WIN;
        }
        if (dealer.isFirstBlackJack()) {
            return LOSE;
        }
        return decideByScore(player.getScore(), dealer.getScore());
    }

    private static Outcome decideByScore(Score playerScore, Score dealerScore) {
        if (playerScore.isBust()) {
            return LOSE;
        }
        if (dealerScore.isBust()) {
            return WIN;
        }
        return compareScore(playerScore, dealerScore);
    }

    private static Outcome compareScore(Score playerScore, Score dealerScore) {
        if (playerScore.isGreaterThan(dealerScore)) {
            return WIN;
        }
        if (playerScore.isLessThan(dealerScore)) {
            return LOSE;
        }
        return DRAW;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }
}
