package blackjack.domain.card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cards {

    private static final int MINIMUM_HIT_CARD_COUNT = 2;

    private final Set<Card> cards;

    public Cards(Set<Card> cards) {
        this.cards = cards;
    }

    public Cards(List<Card> cards) {
        this.cards = new HashSet<>(cards);
    }

    public Cards(Card... cards) {
        this(List.of(cards));
    }

    public Cards() {
        this(new HashSet<>());
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackjack() {
        final Score score = new Score(calculateScore());
        return score.isBlackjack(cards.size());
    }

    public boolean isBust() {
        final Score score = new Score(calculateScore());
        return score.isBust();
    }

    public boolean isReady() {
        return cards.size() < MINIMUM_HIT_CARD_COUNT;
    }

    public int calculateScore() {
        final Score sumScore = calculateCardsSum();
        if (hasAce(cards)) {
            return sumScore.plusAcePoint();
        }
        return sumScore.getScore();
    }

    private boolean hasAce(Set<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private Score calculateCardsSum() {
        return new Score(cards.stream()
                .mapToInt(Card::getScore)
                .sum());
    }

    public List<Card> getCards() {
        return cards.stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
