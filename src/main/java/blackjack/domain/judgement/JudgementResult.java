package blackjack.domain.judgement;

import blackjack.domain.participant.PlayerMoney;

public enum JudgementResult {

    BLACKJACK_WIN(1.5),
    WIN(1),
    TIE(0),
    LOSE(-1);

    private final double profitMultiplier;

    JudgementResult(double profitMultiplier) {
        this.profitMultiplier = profitMultiplier;
    }

    public PlayerMoney calculateProfit(PlayerMoney money) {
        return money.multiply(profitMultiplier);
    }
}
