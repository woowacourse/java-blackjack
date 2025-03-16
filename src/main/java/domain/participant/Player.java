package domain.participant;

import domain.card.TrumpCard;
import domain.participant.state.HandState;
import java.util.List;

public class Player {
    private final Participant participant;
    private final Bet betMoney;

    public Player(ParticipantName name, Bet betMoney, List<TrumpCard> initCards) {
        participant = new Participant(name, initCards);
        this.betMoney = betMoney;
    }

    boolean isDrawable() {
        return !participant.isBust();
    }

    public ParticipantName name() {
        return participant.name();
    }

    public Score calculateCardSum() {
        return participant.calculateCardSum();
    }

    public List<TrumpCard> cards() {
        return participant.trumpCards();
    }

    public void addCard(TrumpCard trumpCard) {
        participant.addDraw(trumpCard);
    }

    public int getProfitFromOpponents(HandState other) {
        HandState hand = participant.handState();
        double profitRate = hand.calculateProfitRate(other);
        return betMoney.calculateBenefit(profitRate);
    }

    public void stay() {
        participant.stay();
    }

    public HandState handState() {
        return participant.handState();
    }

    public boolean isFinished() {
        return participant.isFinished();
    }
}
