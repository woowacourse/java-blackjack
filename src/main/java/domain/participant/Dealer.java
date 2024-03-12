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
        if (participant.isBlackJack() && isBlackJack()) {
            return WinStatus.DRAW;
        }
        if (participant.isBlackJack()) {
            return WinStatus.BLACKJACK;
        }
        return calculateParticipantWinStatusWhenNotBlackJack(participant);
    }

    private WinStatus calculateParticipantWinStatusWhenNotBlackJack(Participant participant) {
        int participantScore = participant.getScore();
        int dealerScore = getScore();
        if (participantScore == dealerScore) {
            return calculateParticipantWinStatusByCardCount(participant);
        }
        if (participantScore > dealerScore) {
            return WinStatus.WIN;
        }
        return WinStatus.LOSE;
    }

    private WinStatus calculateParticipantWinStatusByCardCount(Participant participant) {
        if (participant.getCardCount() < getCardCount()) {
            return WinStatus.WIN;
        }
        return WinStatus.LOSE;
    }
}
