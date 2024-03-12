package blackjack.domain.card;

import blackjack.domain.rule.Score;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Hands {

    private static final int ACE_SCORE_GAP = CardNumber.ACE.getNumber() - CardNumber.ACE_BONUS_NUMBER;

    private final List<Card> cards;

    public Hands() {
        this.cards = new ArrayList<>();
    }

    public Hands(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Hands addCard(final Card card) {
        final List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return new Hands(newCards);
    }

    public Score calculateScore() {
        int sum = sum();
        int aceCount = countAce();

        while (isDeadScore(sum) && aceCount-- > 0) {
            sum -= ACE_SCORE_GAP;
        }

        return new Score(sum);
    }

    private int sum() {
        return cards.stream().mapToInt(Card::getNumber).sum();
    }

    private int countAce() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    private boolean isDeadScore(final int sum) {
        return new Score(sum).isDead();
    }

    public boolean isScoreBiggerThan(final Hands other) {
        return this.calculateScore().isBiggerThan(other.calculateScore());
    }

    public boolean isScoreSame(final Hands other) {
        return this.calculateScore().equals(other.calculateScore());
    }

    public boolean isSizeOf(final int size) {
        return this.size() == size;
    }

    public int size() {
        return cards.size();
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
