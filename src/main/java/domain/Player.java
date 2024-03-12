package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {
    private static final int ACE_LOW = 1;
    private static final int ACE_HIGH = 11;
    private static final int BUST_CONDITION = 21;

    private final Name name;
    private final List<Card> cards = new ArrayList<>();

    public Player(final Name name) {
        this.name = name;
    }

    public void dealCards(final List<Card> initialCards) {
        cards.addAll(initialCards);
    }

    public void dealCard(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = 0;
        for (final Card card : cards) {
            score += determineScore(card, score);
        }
        return score;
    }

    private int determineScore(final Card card, final int score) {
        if (card.isAce()) {
            return determineAceScore(score);
        }
        return card.score();
    }

    private int determineAceScore(final int score) {
        if (score + ACE_HIGH <= BUST_CONDITION) {
            return ACE_HIGH;
        }
        return ACE_LOW;
    }

    public boolean isBust() {
        return calculateScore() > BUST_CONDITION;
    }

    public boolean isNotBust() {
        return calculateScore() <= BUST_CONDITION;
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
