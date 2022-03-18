package blackjack.domain.participant;

import blackjack.domain.betting.BettingMoney;
import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private static final int BUST_THRESHOLD_NUMBER = 21;

    protected ParticipantCards participantCards;
    protected BettingMoney bettingMoney;

    public void receiveInitCards(List<Card> cards) {
        participantCards = new ParticipantCards(new ArrayList<>(cards));
    }

    public void receiveCard(Card card) {
        participantCards.addCard(card);
    }

    public void createBettingMoney(BettingMoney bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public boolean isBust() {
        return getScore() > BUST_THRESHOLD_NUMBER;
    }

    public boolean isBlackjack() {
        return participantCards.isBlackjack();
    }

    public int getScore() {
        return participantCards.calculateScore();
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }
//
//    public void changeBettingMoney(double earningRate) {
//        (int) bettingMoney * earningRate;
//    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(participantCards.getCards());
    }

    public abstract boolean isHittable();

    public abstract String getName();

}
