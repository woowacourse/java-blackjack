package domain.participant;

import domain.card.Card;
import domain.game.GameResult;
import java.math.BigDecimal;
import java.util.List;

public final class Dealer extends Participant {

    private static final int STANDARD_GIVEN_SCORE = 17;

    private Dealer(final String name) {
        super(name);
    }

    public static Dealer create() {
        return new Dealer(DEALER_NAME);
    }

    public GameResult calculateResult(Participant player) {
        return calculateGameResult(this.participantCard, player.participantCard);
    }

    @Override
    public boolean canDraw() {
        return participantCard.canDraw(STANDARD_GIVEN_SCORE);
    }

    private GameResult calculateGameResult(final ParticipantCard dealerCard, final ParticipantCard playerCard) {
        if (checkPlayerBlackJackWin(dealerCard, playerCard)) {
            return GameResult.BLACKJACK_WIN;
        }
        if (checkDealerWin(dealerCard, playerCard)) {
            return GameResult.LOSE;
        }
        if (checkPlayerWin(dealerCard, playerCard)) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    private boolean checkPlayerBlackJackWin(final ParticipantCard dealerCard, final ParticipantCard playerCard) {
        return playerCard.checkBlackJack() && !dealerCard.checkBlackJack();
    }

    private boolean checkDealerWin(final ParticipantCard dealerCard, final ParticipantCard playerCard) {
        return playerCard.checkBust()
                || dealerCard.checkBlackJack() && !playerCard.checkBlackJack()
                || !dealerCard.checkBust() && dealerCard.checkGreaterScoreThan(playerCard);
    }

    private boolean checkPlayerWin(final ParticipantCard dealerCard, final ParticipantCard playerCard) {
        return dealerCard.checkBust()
                || playerCard.checkGreaterScoreThan(dealerCard);
    }

    @Override
    public List<Card> getStartCard() {
        return List.of(participantCard.getFirstCard());
    }
}
