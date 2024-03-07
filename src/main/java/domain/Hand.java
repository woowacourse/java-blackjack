package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public void saveCard(final Card card) {
        cards.add(card);
    }

    public void saveCards(final List<Card> cards) {
        this.cards.addAll(cards);
    }

    // TODO: indent 줄이기
    public int calculateScoreWhileDraw() {
        int sum = 0;
        for (Card card : cards) {
            if (card.isAceCard()) {
                sum += 1;
                continue;
            }
            sum += card.getScore();
        }
        return sum;
    }

    // TODO: indent 줄이기
    public int calculateScore(final int blackJackScore) {
        int sum = cards.stream()
                .map(Card::getScore)
                .mapToInt(score -> score)
                .sum();
        int aceCardCount = 0;
        for (Card card : cards) {
            if (card.isAceCard()) {
                aceCardCount++;
            }
        }
        while (aceCardCount > 0 && sum > blackJackScore) {
            aceCardCount--;
            sum -= 10;
        }
        return sum;
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }

    // TODO: 매직 넘버 상수화
}
