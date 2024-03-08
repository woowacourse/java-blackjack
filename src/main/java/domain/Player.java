package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Player {
    private final Name name;
    private final List<Card> cards = new ArrayList<>();

    public Player(final Name name) {
        this.name = name;
    }

    public void hit(final Card card) {
        cards.add(card);
    }

    public abstract boolean isNotBust();

    public int calculateScore() {
        int score = 0;
        for (final Card card : cards) {
            score += determineScore(card, score);
        }
        return score;
    }

    private int determineScore(final Card card, final int score) {
        if (card.getDenomination() == Denomination.ACE) {
            return determineAceScore(score);
        }
        return card.getValue();
    }

    private int determineAceScore(final int score) {
        if (score + 11 <= 21) {
            return 11;
        }
        return 1;
    }

    public boolean isDealer() {
        return this.name.equals(new Name("딜러"));
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
