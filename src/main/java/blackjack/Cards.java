package blackjack;

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
        return new Score(bestScore(getPossibleScore()));
    }

    private int bestScore(List<Integer> temp) {
        return temp.stream()
                .map(Score::new)
                .filter(tmp -> !tmp.isBust())
                .mapToInt(Score::getValue)
                .max().orElse(hardHand());
    }

    private List<Integer> getPossibleScore() {
        return Stream.concat(Stream.of(hardHand()), softHands().stream())
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

    @Override
    public String toString() {
        return cards.toString();
    }
}
