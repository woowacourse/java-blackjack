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

    public WinStatus isWinner(Participant participant) {
        if (participant.isBust()) {
            return WinStatus.LOSE;
        }
        if (this.isBust()) {
            return WinStatus.WIN;
        }
        return isWinnerWhenNotBust(participant);
    }

    private WinStatus isWinnerWhenNotBust(Participant participant) {
        if (participant.isBlackJack() && this.isBlackJack()) {
            return WinStatus.DRAW;
        }
        if (participant.isBlackJack()) {
            return WinStatus.BLACKJACK;
        }
        return isWinnerWhenNotBlackJack(participant);
    }

    private WinStatus isWinnerWhenNotBlackJack(Participant participant) {
        int participantScore = participant.getScore();
        int dealerScore = this.getScore();
        if (participantScore == dealerScore) {
            return isWinnerByCardCount(participant);
        }
        if (participantScore > dealerScore) {
            return WinStatus.WIN;
        }
        return WinStatus.LOSE;
    }

    private WinStatus isWinnerByCardCount(Participant participant) {
        if (participant.getCardCount() < this.getCardCount()) {
            return WinStatus.WIN;
        }
        return WinStatus.LOSE;
    }
}
