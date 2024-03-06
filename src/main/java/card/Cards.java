package card;

import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int countMaxScore() {
        int aceCardCount = countAceCard();
        int minResultScore = countRoundScore();

        if (aceCardCount == 0) {
            return minResultScore;
        }

        for (int i = 0; i < aceCardCount; i++) {
            if (minResultScore + 10 <= 21) {
                minResultScore += 10;
            }
        }
        return minResultScore;
    }

    public int countRoundScore() {
        return cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    private int countAceCard() {
        return (int) cards.stream()
                .filter(card -> card.isSameCardNumber(CardNumber.ACE))
                .count();
    }
}
