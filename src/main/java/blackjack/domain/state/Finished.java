package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

public abstract class Finished extends Started{

    private static final String FINISHED_DRAW_ERROR_MESSAGE = "[ERROR] 턴이 끝난 후 카드를 새로 뽑을 수 없습니다.";
    private static final String FINISHED_STAY_ERROR_MESSAGE = "[ERROR] 턴이 끝난 후 스테이를 외칠 수 없습니다.";

    public Finished(ParticipantCards participantCards) {
        super(participantCards);
    }

    @Override
    public int getScore() {
        return participantCards.calculateScore();
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException(FINISHED_DRAW_ERROR_MESSAGE);
    }

    @Override
    public State stay() {
        throw new IllegalStateException(FINISHED_STAY_ERROR_MESSAGE);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit(double money, State dealerState) {
        return earningRate(dealerState) * money;
    }

    abstract double earningRate(State dealerState);

}
