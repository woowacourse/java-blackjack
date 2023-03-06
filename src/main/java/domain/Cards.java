package domain;

import java.util.List;

public class Cards {

    private static final int BLACK_JACK = 21;
    private static final int NONE_ACE = 0;
    private static final int ACE_DECREASE = 10;

    private final List<Card> participantCards;

    public Cards(List<Card> participantCards) {
        this.participantCards = participantCards;
    }

    public int getSize() {
        return participantCards.size();
    }

    public int calculateScore(int limit) {
        int aceCount = 0;
        int sum = 0;
        for (Card card : participantCards) {
            aceCount = increaseAceCount(aceCount, card);
            sum += card.getValue().getScore();
        }

        return decreaseScoreByAce(sum, limit, aceCount);
    }

    public void addNewCard(Card card) {
        participantCards.add(card);
    }

    private int increaseAceCount(int aceCount, Card card) {
        if (card.isAce()) {
            aceCount++;
        }

        return aceCount;
    }

    private int decreaseScoreByAce(int sum, int limit, int aceCount) {
        while (isScoreDecreasableByAce(sum, limit, aceCount)) {
            sum -= ACE_DECREASE;
            aceCount--;
        }

        return sum;
    }

    private boolean isScoreDecreasableByAce(int sum, int limit, int aceCount) {
        return sum != BLACK_JACK && limit < sum && NONE_ACE < aceCount;
    }

    public List<Card> getParticipantCards() {
        return List.copyOf(participantCards);
    }

    public Card getFirstCard() {
        return participantCards.get(0);
    }

}