package domain;

import java.util.List;

public class Cards {

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
            sum += card.getValue().getValue();
        }

        return decreaseScoreByAce(sum, limit, aceCount);
    }

    public void addNewCard(Card card) {
        cards.add(card);
    }

    private int increaseAceCount(int aceCount, Card card) {
        if (card.isAce()) {
            return aceCount + 1;
        }

        return aceCount;
    }

    private int decreaseScoreByAce(int sum, int limit, int aceCount) {
        while (isScoreDecreasableByAce(sum, limit, aceCount)) {
            sum -= 10;
            aceCount--;
        }

        return sum;
    }

    private boolean isScoreDecreasableByAce(int sum, int limit, int aceCount) {
        return sum != 21 && limit < sum && 0 < aceCount;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

}
