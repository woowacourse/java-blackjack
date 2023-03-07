package domain;

import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int BLACK_JACK = 21;
    private static final int NONE_ACE = 0;
    private static final int ACE_DECREASE = 10;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int getSize() {
        return cards.size();
    }

    public int calculateScore(int limit) {
        int aceCount = 0;
        int sum = 0;

        for (Card card : cards) {
            aceCount = increaseAceCount(aceCount, card);
            sum += card.getLetterScore();
        }

        return decreaseScoreByAce(sum, limit, aceCount);
    }

    public void addNewCard(Card card) {
        cards.add(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card getCard() {
        return cards.get(cards.size() - 1);
    }

    public void removeCard() {
        cards.remove(cards.size() - 1);
    }

    private int increaseAceCount(int aceCount, Card card) {
        if (card.isAce()) {
            aceCount++;
        }

        return aceCount;
    }

    private int decreaseScoreByAce(int sum, int limit, int aceCount) {
        while (canDecreaseScoreByAce(sum, limit, aceCount)) {
            sum -= ACE_DECREASE;
            aceCount--;
        }

        return sum;
    }

    private boolean canDecreaseScoreByAce(int sum, int limit, int aceCount) {
        return sum != BLACK_JACK && limit < sum && NONE_ACE < aceCount;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

}
