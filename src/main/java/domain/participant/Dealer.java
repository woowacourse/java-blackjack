package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import domain.game.GameResult;

public final class Dealer {

    private static final int STANDARD_GIVEN_SCORE = 17;
    private static final int START_CARD_INDEX = 0;

    private final Participant participant;

    private Dealer(final Participant participant) {
        this.participant = participant;
    }

    public static Dealer create(final String name) {
        final Participant participant = Participant.create(name);

        return new Dealer(participant);
    }

    public void addCard(final Card drawCard) {
        participant.addCard(drawCard);
    }

    public int playDealerTurn(final Deck deck) {
        int drawCardCount = 0;

        while (participant.canDraw(STANDARD_GIVEN_SCORE)) {
            final Card drawCard = deck.draw();

            participant.addCard(drawCard);
            drawCardCount++;
        }
        return drawCardCount;
    }

    public GameResult calculateResult(final ParticipantCard playerCard) {
        final ParticipantCard dealerCard = participant.participantCard();

        return calculateGameResult(dealerCard, playerCard);
    }

    public int calculateScore() {
        final ParticipantScore participantScore = participant.calculateScore();

        return participantScore.score();
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

    public ParticipantCard participantCard() {
        return participant.participantCard();
    }

    public String getName() {
        return participant.getName();
    }

    public Card getStartCard() {
        final ParticipantCard participantCard = participant.participantCard();

        return participantCard.getCards().get(START_CARD_INDEX);
    }
}
