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

    public Score mixHandScore() {
        return possibleScores().stream()
            .filter(not(Score::isBust))
            .reduce(this::bestScore)
            .orElse(Score.hardHandScore(cards));
    }

    public Score softHandScore() {
        int score = cards.stream()
                .mapToInt(Card::softRank)
                .sum();
        return new Score(score);
    }

    private List<Score> possibleScores() {
        return Stream.concat(Stream.of(hardHand()), softHands().stream())
            .map(Score::new)
            .collect(toUnmodifiableList());
    }

    private int hardHand() {
        return cards.stream()
            .mapToInt(Card::hardRank)
            .sum();
    }

    private List<Integer> softHands() {
        return IntStream.rangeClosed(1, numberOfAce())
            .map(value -> diffSoftAndHard(Rank.ACE) * value + hardHand())
            .boxed()
            .collect(toList());
    }

    private int diffSoftAndHard(Rank rank) {
        return rank.soft() - rank.hard();
    }

    private int numberOfAce() {
        return (int) cards.stream()
            .filter(Card::isAce)
            .count();
    }

    private Score bestScore(Score score1, Score score2) {
        if (score1.moreThan(score2)) {
            return score1;
        }
        return score2;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
