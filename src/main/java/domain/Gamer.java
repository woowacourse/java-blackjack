package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Gamer implements Cloneable {
    private final Set<Card> cards;

    protected Gamer() {
        this.cards = new HashSet<>();
    }

    public int calculateScore() {
        boolean containAce = cards.stream()
                .anyMatch(card -> card.getCardRank() == CardRank.ACE);

        int cardScore = cards.stream()
                .mapToInt(card -> card.getCardRank().getValue())
                .sum();

        if (!containAce || cardScore > 21) {
            return cardScore;
        }

        int otherScore = cardScore - CardRank.ACE.getValue() + CardRank.SPECIAL_ACE.getValue();

        if (otherScore > 21) {
            return cardScore;
        }

        return otherScore;
    }

    public void addCard(List<Card> addedCards) {
        cards.addAll(addedCards);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public boolean canGetMoreCard(int standard) {
        int score = calculateScore();
        return score <= standard;
    }

    @Override
    public Gamer clone() {
        try {
            Gamer clone = (Gamer) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
