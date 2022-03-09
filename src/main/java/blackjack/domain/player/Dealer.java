package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Dealer implements Player {

    private static final int ADD_CARD_CONDITION = 16;

    private final Cards cards;

    private int winCount = 0;
    private int loseCount = 0;

    public Dealer(final List<Card> cards) {
        this.cards = new Cards(cards);
    }

    public boolean acceptableCard() {
        return cards.calculateScoreByAceEleven() <= ADD_CARD_CONDITION;
    }

    @Override
    public void addCard(final Card card) {
        this.cards.addCard(card);
    }

    @Override
    public int calculateScore() {
        final int scoreByAceOne = cards.calculateScoreByAceOne();
        final int scoreByAceEleven = cards.calculateScoreByAceEleven();

        if (scoreByAceEleven <= MAX_SCORE) {
            return scoreByAceEleven;
        }
        return scoreByAceOne;
    }

    public void compete(final Participant participant) {
        final int dealerScore = calculateScore();
        final int participantScore = participant.calculateScore();

        if (isDealerWin(dealerScore, participantScore)) {
            winCount += 1;
            return;
        }
        participant.win();
        this.loseCount += 1;
    }

    private boolean isDealerWin(int dealerScore, int participantScore) {
        return participantScore > MAX_SCORE || (dealerScore <= MAX_SCORE && dealerScore >= participantScore);
    }

    @Override
    public List<Card> getCards() {
        return cards.getCards();
    }

    public Card getCardOne(){
        return cards.getCards().get(0);
    }
}
