package blackjack.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Cards {

    private final List<Card> cards;

    Cards(Card card1, Card card2, Card... cards) {
        this.cards = concat(concat(card1, card2), cards)
            .collect(Collectors.toList());
    }

    private Stream<Card> concat(Card card1, Card card2) {
        return Stream.concat(Stream.of(card1), Stream.of(card2));
    }

    private Stream<Card> concat(Stream<Card> cards1, Card[] cards2) {
        return Stream.concat(cards1, List.of(cards2).stream());
    }

    public Score maxScore() {
        int score = stream()
            .mapToInt(Card::softRank)
            .sum();
        return new Score(score);
    }

    public Stream<Card> stream() {
        return cards.stream();
    }

    public void take(Card card) {
        cards.add(card);
    }

    public Score bestScore() {
        if (hasAce() && !softHandScore().isBust()) {
            return softHandScore();
        }
        return hardHandScore();
    }

    private boolean hasAce() {
        return stream().anyMatch(Card::isAce);
    }

    private Score softHandScore() {
        return hardHandScore().plus(increaseScore());
    }

    private Score increaseScore() {
        return new Score(Rank.ACE.soft() - Rank.ACE.hard());
    }

    private Score hardHandScore() {
        int score = cards.stream()
            .mapToInt(Card::hardRank)
            .sum();
        return new Score(score);
    }
}
