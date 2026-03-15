package domain.participant;

import domain.card.Card;

import java.math.BigDecimal;

public abstract class Participant {
    private static final int BLACKJACK_SATISFYING_HAND_COUNT = 2;
    private static final int BLACKJACK_SATISFYING_SCORE = 21;
    private final ParticipantInfo participantInfo;
    private final Money bettingMoney;

    public Participant(ParticipantInfo participantInfo, Money bettingMoney) {
        this.participantInfo = participantInfo;
        this.bettingMoney = bettingMoney;
    }

    public abstract void keepCard(Card card);

    public abstract boolean canHit();

    public int handSize() {
        return participantInfo.handSize();
    }

    public int getTotalCardScore() {
        return participantInfo.getTotalCardScore();
    }

    public String getName() {
        return participantInfo.getName();
    }

    public Hand getHand() {
        return participantInfo.getHand();
    }

    public BigDecimal getBettingMoney() {
        return bettingMoney.getBettingMoney();
    }

    public boolean isMaxScore() {
        return getTotalCardScore() == BLACKJACK_SATISFYING_SCORE;
    }

    public boolean isBlackJack() {
        return handSize() == BLACKJACK_SATISFYING_HAND_COUNT && getTotalCardScore() == BLACKJACK_SATISFYING_SCORE;
    }
}
