package blackjack;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Cards {

    private final List<Card> cards;

    public Cards(Card... cards) {
        this.cards = List.of(cards);
    }

    public Score score() {
        return possibleScores().stream()
            .filter(not(Score::isBust))
            .reduce(this::bestScore)
            .orElse(new Score(hardHand()));
    }

    private List<Score> possibleScores() {
        return Stream.concat(Stream.of(hardHand()), softHands().stream())
            .map(Score::new)
            .collect(toUnmodifiableList());
    }

    public int hardHand() {
        return cards.stream()
            .mapToInt(Card::getRank)
            .sum();
    }

    public List<Integer> softHands() {
        return IntStream.rangeClosed(1, numberOfAce())
            .map(value -> 10 * value + hardHand())
            .boxed()
            .collect(toList());
    }

    private int numberOfAce() {
        return (int) cards.stream()
            .filter(Card::isAce)
            .count();
    }

    public Score bestScore(Score a, Score b) {
        return Integer.compare(a.getValue(), b.getValue()) == -1 ? b : a;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
