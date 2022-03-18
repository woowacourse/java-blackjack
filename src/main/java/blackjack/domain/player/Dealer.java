package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public final class Dealer extends Player {

    private static final int ADD_CARD_CONDITION = 16;
    private static final int FIRST = 0;
    private static final int BET_AMOUNT = 0;
    private static final String NAME = "딜러";

    public Dealer(final List<Card> cards) {
        super(cards, NAME, new Bet(BET_AMOUNT));
    }

    @Override
    public boolean acceptableCard() {
        return getMaxScore() <= ADD_CARD_CONDITION;
    }

    public void compete(final Participant participant) {
        if (isBlackjack(participant)) {
            return;
        }
        if (isDealerWin(participant)) {
            return;
        }
        isParticipantWin(participant);
    }

    private boolean isBlackjack(final Participant participant) {
        if (isDealerParticipantBlackJack(participant)) {
            this.bothBlackjack();
            return true;
        }
        if (isParticipantBlackJack(participant)) {
            participant.blackjack();
            return true;
        }
        return false;
    }

    private boolean isDealerWin(final Participant participant) {
        if (isDealerWin(calculateFinalScore(), participant.calculateFinalScore())) {
            this.win();
            participant.lose();
            return true;
        }
        return false;
    }

    private void isParticipantWin(final Participant participant) {
        participant.win();
        this.lose();
    }

    private boolean isDealerParticipantBlackJack(final Participant participant) {
        return this.calculateInitCardScore() == MAX_SCORE && participant.calculateInitCardScore() == MAX_SCORE;
    }

    private boolean isParticipantBlackJack(final Participant participant) {
        return this.calculateInitCardScore() != MAX_SCORE && participant.calculateInitCardScore() == MAX_SCORE;
    }

    private boolean isDealerWin(final int dealerScore, final int participantScore) {
        return participantScore > MAX_SCORE || (dealerScore <= MAX_SCORE && dealerScore >= participantScore);
    }

    public void calculateDealerProfit(final List<Participant> participants) {
        List<Integer> profits = convertProfits(participants);
        getBet().calculateFinalProfit(profits);
    }

    private List<Integer> convertProfits(final List<Participant> participants) {
        List<Integer> profits = new ArrayList<>();
        for (Participant participant : participants) {
            profits.add(participant.getBetProfit());
        }
        return profits;
    }

    public Card getCardFirstOne() {
        return getCards().get(FIRST);
    }
}
