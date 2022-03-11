package blackjack.model;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.model.Card;
import blackjack.model.Rank;
import blackjack.model.Score;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
        return softHandScores().stream()
                .filter(not(Score::isBust))
                .reduce(this::bestScore)
                .orElse(hardHandScore());
    }

    private List<Score> softHandScores() {
        return IntStream.rangeClosed(1, numberOfAce())
            .mapToObj(count -> hardHandScore().plus(increaseScore(count)))
            .collect(toUnmodifiableList());
    }

    private int numberOfAce() {
        return (int) stream()
            .filter(Card::isAce)
            .count();
    }

    private Score increaseScore(int numberOfAce) {
        return new Score(diffSoftAndHardOfAce() * numberOfAce);
    }

    private int diffSoftAndHardOfAce() {
        return Rank.ACE.soft() - Rank.ACE.hard();
    }

    private Score bestScore(Score score1, Score score2) {
        if (score1.moreThan(score2)) {
            return score1;
        }
        return score2;
    }

    private Score hardHandScore() {
        int score = cards.stream()
            .mapToInt(Card::hardRank)
            .sum();
        return new Score(score);
    }
}
