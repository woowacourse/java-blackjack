package domain.participant;

import domain.blackjack.Deck;
import domain.blackjack.WinStatus;
import domain.card.Card;

public class Dealer extends Participant {

    private static final int DEALER_HIT_COUNT = 16;

    private final Deck deck;

    public Dealer() {
        super(new Name("딜러"));
        deck = new Deck();
    }

    public boolean shouldHit() {
        return hands.calculateScore() <= DEALER_HIT_COUNT;
    }

    public Card draw() {
        return deck.draw();
    }

    public void receiveCard() {
        super.receiveCard(deck.draw());
    }

    public boolean isNotBlackJack() {
        return !isBlackJack();
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
}
