package domain;

import java.util.List;

public class Player {

    private final List<Card> cards;
    private final String name;

    private Player(List<Card> cards, String name) {
        this.cards = cards;
        this.name = name;
    }

    public static Player of(List<Card> cards, String name) {
        return new Player(cards, name);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int cardScore = calculateRawScore();
        int aceCount = countAce();

        for (int i = 0; i < aceCount; i++) {
            cardScore = adjustForAce(cardScore);
        }

        return cardScore;
    }

    private int calculateRawScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int countAce() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    private int adjustForAce(int cardScore) {
        if (isBust(cardScore)) {
            cardScore -= Policy.ACE_HIGH_LOW_DIFF;
        }
        return cardScore;
    }

    public boolean isBust(int cardScore) {
        return cardScore > Policy.BUST_THRESHOLD;
    }

    public int getScoreOrZeroIfBust() {
        int score = calculateScore();
        if (isBust(score)) {
            return Policy.BUST_SCORE;
        }
        return score;
    }

    public List<String> getCardsInfo() {
        return cards.stream()
                .map(Card::getName)
                .toList();
    }

    public String getName() {
        return name;
    }
}
