package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private static final int ACE_HIGH_LOW_DIFF = 10;
    private static final int BUST_THRESHOLD = 21;

    private final List<Card> cards = new ArrayList<>();
    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public void addInitialCards(List<Card> cards) {
        cards.forEach(this::addCard);
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
        if (cardScore > BUST_THRESHOLD) {
            return cardScore - ACE_HIGH_LOW_DIFF;
        }
        return cardScore;
    }

    public boolean isBust() {
        return calculateScore() > BUST_THRESHOLD;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public String getName() {
        return name;
    }
}
