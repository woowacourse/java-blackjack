package domain.participant;

import domain.card.Card;
import java.util.List;

public class Participant {

    private static final int BUST_BOUNDARY_EXCLUSIVE = 21;
    private static final int BONUS_SCORE = 10;
    private final Name name;
    private final List<Card> cards;

    public Participant(final Name name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        int nonAceSum = cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getDefaultScore)
                .sum();
        int aceDefaultSum = (int) cards.stream().filter(Card::isAce).count();
        int totalScore = nonAceSum + aceDefaultSum;
        for (int i = 0; i < aceDefaultSum; i++) {
            if (totalScore + BONUS_SCORE <= BUST_BOUNDARY_EXCLUSIVE) {
                totalScore += BONUS_SCORE;
            }
        }
        return totalScore;
    }

    public boolean isBust() {
        return calculateScore() > BUST_BOUNDARY_EXCLUSIVE;
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name=" + getName() +
                ", cards=" + getCards() +
                '}';
    }
}
