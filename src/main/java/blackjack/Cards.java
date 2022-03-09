package blackjack;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Cards {

    private final List<String> cards;

    public Cards(String... cards) {
        this.cards = List.of(cards);
    }

    public Score score() {
        return new Score(bestScore(getPossibleScore()));
    }

    private int bestScore(List<Integer> temp) {
        return temp.stream()
                .filter(tmp -> !isBust(tmp))
                .mapToInt(Integer::intValue)
                .max().orElse(hardHand());
    }

    private List<Integer> getPossibleScore() {
        return Stream.concat(Stream.of(hardHand()), softHands().stream())
                .collect(toUnmodifiableList());
    }

    public int hardHand() {
        int result = 0;
        for (String card : cards) {
            String value = card.substring(0, 1);
            result += number(value);
        }
        return result;
    }

    public List<Integer> softHands() {
        return IntStream.rangeClosed(1, numberOfAce())
                .map(value -> 10 * value + hardHand())
                .boxed()
                .collect(toList());
    }

    private int numberOfAce() {
        return (int) cards.stream()
                .map(s -> s.substring(0, 1))
                .filter(s -> s.equals("A"))
                .count();
    }

    private boolean isBust(int score) {
        return score > 21;
    }

    public boolean isBust() {
        return score().isBust();
    }

    private int number(String value) {
        if (value.equals("J") || value.equals("Q") || value.equals("K")) {
            return 10;
        }
        if (value.equals("A")) {
            return 1;
        }
        return Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
