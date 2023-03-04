package domain.participant;

import domain.card.Card;

public class Dealer extends Participant {

    private static final int STANDARD_GIVEN_SCORE = 16;

    private Dealer(final String name) {
        super(name);
    }

    public static Dealer create() {
        return new Dealer(DEALER_NAME);
    }

    public Result calculateResult(Participant player) {
        final ParticipantCard playerCard = player.participantCard;

        if (isDealerWin(playerCard)) {
            return Result.LOSE;
        }
        if (isPlayerWin(playerCard)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    private boolean isDealerWin(final ParticipantCard playerCard) {
        final ParticipantCard dealerCard = this.participantCard;

        return playerCard.isBust()
                || dealerCard.isBlackJack()
                || dealerCard.isBust() && playerCard.isBust()
                || !dealerCard.isBust() && dealerCard.calculateScore() > playerCard.calculateScore();
    }

    private boolean isPlayerWin(final ParticipantCard playerCard) {
        final ParticipantCard dealerCard = this.participantCard;

        return dealerCard.isBust()
                || playerCard.isBlackJack()
                || dealerCard.calculateScore() < playerCard.calculateScore();
    }

    public boolean canGiveCard() {
        return participantCard.calculateScore() <= STANDARD_GIVEN_SCORE;
    }

    public Card getFirstCard() {
        return participantCard.getFirstCard();
    }
}
