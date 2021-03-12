package blackjack.domain.gametable;

import blackjack.domain.card.Score;

public enum Outcome {
    WIN("승", 1.5),
    LOSE("패", 0.0),
    DRAW("무", 1.0);

    private final String word;
    private final double earningRate;

    Outcome(String word, double earningRate) {
        this.word = word;
        this.earningRate = earningRate;
    }

    public String getWord() {
        return word;
    }

    public double getEarningRate(){
        return earningRate;
    }

    public static Outcome getInstance(final Score base, final Score counterpart) {
        if (draw(base, counterpart)) {
            return Outcome.DRAW;
        }

        if (win(base, counterpart)) {
            return Outcome.WIN;
        }

        return Outcome.LOSE;
    }

    private static boolean draw(final Score base, final Score counterpart) {
        if (base.isBurst() && counterpart.isBurst()) {
            return true;
        }
        return base.isNotBurst() && counterpart.isNotBurst() && base.isSameAs(counterpart);
    }

    private static boolean win(final Score base, final Score counterpart) {
        if (base.isBlackjack() && counterpart.isNotBlackjack()) {
            return true;
        }
        if (base.isNotBurst() && counterpart.isBurst()) {
            return true;
        }
        return base.isNotBurst() && counterpart.isNotBurst() && base.isHigherThan(counterpart);
    }

}
