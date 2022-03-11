package blackjack.model;

import static java.util.function.Predicate.not;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Cards {

    private final List<Card> cards;

    Cards(Card card1, Card card2, Card... cards) {
        this.cards = Stream.concat(Stream.concat(Stream.of(card1), Stream.of(card2)), List.of(cards).stream())
            .collect(Collectors.toList());
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
        return softHandScore()
                .filter(not(Score::isBust))
                .orElse(hardHandScore());
    }

    private Optional<Score> softHandScore() {
        if (hasAce()) {
            return Optional.of(hardHandScore().plus(increaseScore()));
        }
        return Optional.empty();
    }

    private boolean hasAce() {
        return stream().anyMatch(Card::isAce);
    }

    private Score increaseScore() {
        return new Score(diffSoftAndHardOfAce());
    }

    private int diffSoftAndHardOfAce() {
        return Rank.ACE.soft() - Rank.ACE.hard();
    }

    private Score hardHandScore() {
        int score = cards.stream()
            .mapToInt(Card::hardRank)
            .sum();
        return new Score(score);
    }
}
