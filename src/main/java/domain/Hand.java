package domain;

import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public int getSize() {
        return cards.size();
    }

    public Score calculateScore(Score limit) {
        Score score = calculateCardScoreSum();
        return score.decreaseScoreByAce(limit, calculateAceCount());
    }

    public void addNewCard(Card card) {
        cards.add(card);
    }

    private Score calculateCardScoreSum() {
        Score sum = Score.from(0);
        for (Card card : cards) {
            sum = sum.add(card.getScore());
        }
        return sum;
    }

    private int calculateAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

}