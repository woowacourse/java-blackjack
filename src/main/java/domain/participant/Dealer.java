package domain.participant;

import domain.card.Card;
import domain.card.Score;

import java.util.List;

import static domain.participant.Participant.DEALER_NAME;

public final class Dealer implements Playable {
    private static final int FIRST_CARD_INDEX = 0;
    private static final Score STANDARD_GIVEN_SCORE = Score.create(16);

    private final Participant participant;
    private ParticipantMoney participantMoney;

    private Dealer(final Participant participant) {
        this.participant = participant;
        this.participantMoney = ParticipantMoney.zero();
    }

    static Dealer create() {
        return new Dealer(Participant.create(DEALER_NAME.getName()));
    }

    public Card getFirstCard() {
        return participant.getHand().get(FIRST_CARD_INDEX);
    }

    public void earnMoney(final ParticipantMoney playerMoney) {
        participantMoney = participantMoney.add(playerMoney);
    }

    public void loseMoney(final ParticipantMoney playerMoney) {
        participantMoney = participantMoney.subtract(playerMoney);
    }

    @Override
    public void addCard(final Card... cards) {
        participant.addCard(cards);
    }

    @Override
    public boolean canDrawCard() {
        return STANDARD_GIVEN_SCORE.isGreaterThanAndEqual(participant.calculateScore());
    }

    @Override
    public boolean isBlackJack() {
        return participant.isBlackJack();
    }

    @Override
    public boolean isBust() {
        return participant.isBust();
    }

    @Override
    public int calculateScore() {
        return participant.calculateScore().getScore();
    }

    @Override
    public void resetMoney(final ParticipantMoney initMoney) {
        participantMoney = initMoney;
    }

    public String getName() {
        return DEALER_NAME.getName();
    }

    public List<Card> getHand() {
        return participant.getHand();
    }

    public int getMoney() {
        return (int) participantMoney.getMoney();
    }
}
