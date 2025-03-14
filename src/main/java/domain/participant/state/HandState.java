package domain.participant.state;

import domain.card.TrumpCard;
import domain.game.WinStatus;
import domain.participant.ParticipantHand;
import domain.participant.Score;
import java.util.List;

public abstract class HandState {
    private static final Score MAX_SCORE = Score.from(21);
    private static final int BLACKJACK_CARD_COUNT = 2;
    protected final ParticipantHand hand;

    protected HandState(ParticipantHand hand) {
        this.hand = hand;
    }

    public static HandState start(TrumpCard... initCards){
        HandState start = new Start(initCards);

        if(start.isBlackjack()){
            return new Blackjack(start.hand);
        }

        return new Hit(start.hand);
    }

    public abstract HandState addCard(TrumpCard card);
    public abstract HandState stay();
    public abstract double calculateProfitRate(HandState other);

    public boolean isBust(){
        Score totalScore = hand.calculateCardSum();
        return hand.isBust(totalScore);
    }

    protected boolean isBlackjack(){
        Score totalScore = hand.calculateCardSum();
        return totalScore.isEqualTo(MAX_SCORE) && hand.size() == BLACKJACK_CARD_COUNT;
    }

    public Score calculateCardSum(){
        return hand.calculateCardSum();
    }

    public Score calculateCardSum(Score aceCalculateStandard){
        return hand.calculateCardSum(aceCalculateStandard);
    }

    public List<TrumpCard> getCards(){
        return hand.getCards();
    }

    public WinStatus getWinStatusAgainst(Score other) {
        return hand.getWinStatusAgainst(other);
    }
}
