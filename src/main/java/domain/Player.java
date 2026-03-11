package domain;

import java.util.List;

public class Player {

    public static final int ACE_HIGH_LOW_DIFF = 10;
    public static final int BUST_THRESHOLD = 21;

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
            cardScore -= ACE_HIGH_LOW_DIFF;
        }
        return cardScore;
    }

    public boolean isBust(int cardScore) {
        return cardScore > BUST_THRESHOLD;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public String getName() {
        return name;
    }
}
