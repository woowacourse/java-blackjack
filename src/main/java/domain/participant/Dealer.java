package domain.participant;

import domain.blackjack.Deck;
import domain.blackjack.WinStatus;
import domain.card.Card;

public class Dealer extends Participant {

    private static final int DEALER_HIT_COUNT = 16;

    private Deck deck;

    public Dealer() {
        super(new Name("딜러"));
        this.deck = new Deck();
    }

    public void receiveCard(Card card) {
        super.receiveCard(card);
    }

    public boolean isNotBlackJack() {
        return !isBlackJack();
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    public WinStatus calculateParticipantWinStatus(Participant participant) {
        if (participant.isBust()) {
            return WinStatus.LOSE;
        }
        if (isBust()) {
            return WinStatus.WIN;
        }
        return calculateParticipantWinStatusWhenNotBust(participant);
    }

    private WinStatus calculateParticipantWinStatusWhenNotBust(Participant participant) {
        int participantScore = participant.getScore();
        int dealerScore = getScore();
        if (participantScore == dealerScore) {
            return WinStatus.PUSH;
        }
        if (participantScore > dealerScore) {
            return WinStatus.WIN;
        }
        return WinStatus.LOSE;
    }

    @Override
    public boolean canHit() {
        return hands.calculateScore() <= DEALER_HIT_COUNT;
    }

    public Card draw() {
        return deck.draw();
    }
}
