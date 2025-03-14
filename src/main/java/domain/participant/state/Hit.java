package domain.participant.state;

import domain.card.TrumpCard;
import domain.participant.ParticipantHand;
import domain.participant.Score;

public class Hit extends HandState {
    public Hit(ParticipantHand hand) {
        super(hand);
    }

    @Override
    public HandState addCard(TrumpCard card) {
        hand.addCard(card);
        Score totalScore = hand.calculateCardSum();
        if(hand.isBust(totalScore)){
           return new Bust(hand);
        }
        return this;
    }

    @Override
    public HandState stay() {
        return new Stay(hand);
    }

    @Override
    public double calculateProfit() {
        throw new IllegalStateException("HIT 상태입니다.");
    }
}
