package blackjack.domain.card;

import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public void append(Card card) {
        cards.add(card);
    }

    public int calculateScoreTotalClosestToThreshold(int threshold) {
        int scoreTotal = calculateScoreTotal();
        int aceCount = countAce();
        int aceScoreDifference = CardRank.ACE.getSpecialScore() - CardRank.ACE.getScore();

        while (aceCount > 0 && scoreTotal + aceScoreDifference <= threshold) {
            scoreTotal += aceScoreDifference;
            aceCount--;
        }
        return scoreTotal;
    }

    private int calculateScoreTotal() {
        return cards.stream()
                .map(Card::getCardRank)
                .mapToInt(CardRank::getScore)
                .sum();
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public int sum() {
        return cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    public int countCard() {
        return cards.size();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
