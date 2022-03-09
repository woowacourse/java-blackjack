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

    public int scoreForDealer() {
        int value = 0;
        for (Card card : cards) {
            value += valueForDealer(card);
        }
        return value;
    }

    private int valueForDealer(Card card) {
        if (card.isAce()) {
            return 11;
        }
        return card.getRank();
    }

    private List<Score> possibleScores() {
        return Stream.concat(Stream.of(hardHand()), softHands().stream())
            .map(Score::new)
            .collect(toUnmodifiableList());
    }

    private int hardHand() {
        return cards.stream()
            .mapToInt(Card::getRank)
            .sum();
    }

    private List<Integer> softHands() {
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
