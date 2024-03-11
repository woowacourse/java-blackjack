package domain.player;

import domain.blackjack.OneOnOneResult;
import domain.card.Card;
import domain.card.Denomination;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Player {
    private static final int ACE_LOW = 1;
    private static final int ACE_HIGH = 11;
    static final int PERFECT_SCORE = 21;

    private final Name name;
    private final List<Card> cards = new ArrayList<>();

    public Player(final Name name) {
        this.name = name;
    }

    public abstract boolean isNotBust();

    public abstract boolean isBust();

    public abstract boolean canHit();

    public abstract boolean canNotHit();
    public void hit(final Card card) {
        cards.add(card);
    }

    public abstract OneOnOneResult determineWinnerByComparing(final Player otherPlayer);

    public void clear() {
        this.cards.clear();
    }


    public int calculateScore() {
        int score = 0;
        for (final Card card : cards) {
            score += determineScore(card, score);
        }
        return score;
    }

    public boolean isDealer() {
        return this == Dealer.getInstance();
    }

    public boolean isParticipant() {
        return !this.isDealer();
    }

    private int determineScore(final Card card, final int score) {
        if (card.getDenomination() == Denomination.ACE) {
            return determineAceScore(score);
        }
        return card.getValue();
    }

    private int determineAceScore(final int score) {
        if (score + ACE_HIGH <= PERFECT_SCORE) {
            return ACE_HIGH;
        }
        return ACE_LOW;
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
