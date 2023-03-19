package domain.game;

import domain.participant.ParticipantMoney;

public enum EarningRate {
    BONUS(MoneyMultiplier.create(1.5)),
    WIN(MoneyMultiplier.create(1)),
    LOSE(MoneyMultiplier.create(-1));

    private final MoneyMultiplier multiplier;

    EarningRate(final MoneyMultiplier multiplier) {
        this.multiplier = multiplier;
    }

    public ParticipantMoney calculateMoney(final ParticipantMoney participantMoney) {
        return participantMoney.multiply(multiplier.getMultiplier());
    }
}
