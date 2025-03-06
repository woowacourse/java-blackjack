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

        int lowAceTotal = cards.stream()
                .mapToInt(card -> card.getCardRank().getValue())
                .sum();

        return calculateAceValue(containAce, lowAceTotal);
    }

    public void addCard(List<Card> addedCards) {
        cards.addAll(addedCards);
    }

    public boolean isDrawable(int standard) {
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

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }


    private int calculateAceValue(boolean containAce, int lowAceTotal) {
        if (!containAce || lowAceTotal > 21) {
            return lowAceTotal;
        }
        int highAceTotal = lowAceTotal - CardRank.ACE.getValue() + CardRank.SPECIAL_ACE.getValue();
        if (highAceTotal > 21) {
            return lowAceTotal;
        }
        return highAceTotal;
    }
}
