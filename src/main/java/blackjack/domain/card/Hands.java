package blackjack.domain.card;

import blackjack.domain.rule.Score;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Hands implements Comparable<Hands> {

    private static final Score ACE_SCORE_GAP = CardNumber.ACE.getScore().minus(CardNumber.ACE_BONUS_NUMBER);

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
        Score sum = sum();
        int aceCount = countAce();

        while (sum.isBust() && aceCount-- > 0) {
            sum = sum.minus(ACE_SCORE_GAP);
        }

        return sum;
    }

    private Score sum() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(Score::plus)
                .orElseThrow(() -> new IllegalStateException("카드가 없습니다."));
    }

    private int countAce() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    public boolean isBustScore() {
        return calculateScore().isBust();
    }

    public boolean isBlackjackScore() {
        return calculateScore().isBlackjack();
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public int compareTo(final Hands o) {
        return calculateScore().compareTo(o.calculateScore());
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
