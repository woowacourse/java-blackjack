package domain.participant.state;

import domain.card.TrumpCard;
import domain.participant.ParticipantHand;
import domain.participant.Score;

public class Stay extends HandState {
    private static final double WIN_PROFIT_RATE = 2.0;
    private static final double DRAW_PROFIT_RATE = 1.0;
    private static final double LOSE_PROFIT_RATE = 0;

    public Stay(ParticipantHand hand) {
        super(hand);
    }

    @Override
    public HandState addCard(TrumpCard card) {
        throw new IllegalArgumentException("Stay 상태에서는 카드를 추가할 수 없습니다.");
    }

    @Override
    public HandState stay() {
        return this;
    }

    @Override
    public double calculateProfitRate(HandState other) {
        if(other.isBust()){
            return WIN_PROFIT_RATE;
        }

        return calculateProfitFromOtherScore(other);
    }

    private double calculateProfitFromOtherScore(HandState other){
        Score score = calculateCardSum();
        Score otherScore = other.calculateCardSum();
        if(score.isGreaterThan(otherScore)){
            return WIN_PROFIT_RATE;
        }
        if(score.isLessThan(otherScore)){
            return LOSE_PROFIT_RATE;
        }
        return DRAW_PROFIT_RATE;
    }
}
