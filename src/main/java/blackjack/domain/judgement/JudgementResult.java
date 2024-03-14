package blackjack.domain.judgement;

public enum JudgementResult {

    BLACKJACK_WIN(1.5),
    WIN(1),
    TIE(0),
    LOSE(-1);

    private final double profitMultiplier;

    JudgementResult(double profitMultiplier) {
        this.profitMultiplier = profitMultiplier;
    }

    public double getProfitMultiplier() {
        return profitMultiplier;
    }
}
