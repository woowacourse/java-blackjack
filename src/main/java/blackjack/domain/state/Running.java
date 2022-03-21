package blackjack.domain.state;

import blackjack.domain.card.ParticipantCards;

public abstract class Running extends Started {

    private static final String RUNNING_GET_SCORE_ERROR_MESSAGE = "[ERROR] 게임이 끝나지 않은 경우 점수를 가져올 수 없습니다.";
    private static final String RUNNING_PROFIT_ERROR_MESSAGE = "[ERROR] 게임이 끝나지 않은 경우 수익을 구할 수 없습니다.";

    public Running(ParticipantCards participantCards) {
        super(participantCards);
    }

    @Override
    public ParticipantCards participantCards() {
        return participantCards;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public int getScore() {
        throw new IllegalStateException(RUNNING_GET_SCORE_ERROR_MESSAGE);
    }

    @Override
    public double profit(double money, State dealerState) {
        throw new IllegalStateException(RUNNING_PROFIT_ERROR_MESSAGE);
    }

}
