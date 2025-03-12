package blackjack.model.game;

public record BetAmount(int value) {

    public BetAmount {
        if (value < 0) {
            throw new IllegalArgumentException("최소 0원 이상 배팅해야 합니다.");
        }
    }

    public int calculateProfitAmount(ParticipantResult participantResult) {
        if (participantResult == ParticipantResult.DRAW) {
            return 0;
        }
        if (participantResult == ParticipantResult.LOSE) {
            return -value;
        }
        if (participantResult == ParticipantResult.WIN) {
            return value;
        }
        return (int) (value * 1.5);
    }
}
