package blackjack.model;

import static java.util.function.Predicate.not;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Cards {

    private final List<Card> cards;

    public Cards(Card card1, Card card2, Card... cards) {
        this.cards = Stream.concat(Stream.concat(Stream.of(card1), Stream.of(card2)), List.of(cards).stream())
                .collect(Collectors.toList());
    }

    public void takeCard(Card card) {
        cards.add(card);
    }

    public Score softHandScore() {
        int score = stream()
                .mapToInt(Card::softRank)
                .sum();
        return new Score(score);
    }

    public Stream<Card> stream() {
        return cards.stream();
    }

    public Score bestScore() {
        return applySoftHandScore()
                .filter(not(Score::isBust))
                .orElse(hardHandScore());
    }

    private Score hardHandScore() {
        int score = stream()
                .mapToInt(Card::hardRank)
                .sum();
        return new Score(score);
    }

    private Optional<Score> applySoftHandScore() {
        if (hasAce()) {
            return Optional.of(hardHandScore().plus(diffSoftAndHardOfAce()));
        }
        return Optional.empty();
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    private Score diffSoftAndHardOfAce() {
        return new Score(Rank.ACE.soft() - Rank.ACE.hard());
    }
}
