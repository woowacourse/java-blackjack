package blackjack.model.game;

public record BetAmount(int value) {

    public BetAmount {
        if (value < 0) {
            throw new IllegalArgumentException("최소 0원 이상 배팅해야 합니다.");
        }
    }

    public double calculateProfitAmount(ParticipantResult participantResult) {
        return participantResult.getRate() * value;
    }
}
