package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Gamer {
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

    public void addCard(Card card) {
        cards.add(card);
    }

    public Set<Card> getCards() {
        return Collections.unmodifiableSet(cards);
    }

    public boolean canGetMoreCard(int standard) {
        int score = calculateScore();
        return score <= standard;
    }
}
