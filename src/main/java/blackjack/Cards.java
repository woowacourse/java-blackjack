package blackjack;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Cards {

    private final List<String> cards;

    public Cards(String... cards) {
        this.cards = List.of(cards);
    }

    public Score score() {
        List<Integer> temp = getPossibleScore();
        return new Score(temp.stream()
            .filter(tmp -> !isBust(tmp))
            .mapToInt(Integer::intValue)
            .max().orElse(hardHand()));
    }

    private List<Integer> getPossibleScore() {
        return Stream.concat(Stream.of(hardHand()), softHands().stream())
            .collect(toUnmodifiableList());
    }

    public int hardHand() {
        int result = 0;
        for (String card : cards) {
            String value = card.substring(0, 1);
            result += number(value, 1);
        }
        return result;
    }

    public List<Integer> softHands() {
        return IntStream.rangeClosed(1, numberOfAce())
            .map(value -> 10 * value + hardHand())
            .boxed()
            .collect(toList());
    }

    private boolean hasMoreThanTwoAce() {
        return numberOfAce() >= 2;
    }

    private int numberOfAce() {
        return (int) cards.stream()
            .map(s -> s.substring(0, 1))
            .filter(s -> s.equals("A"))
            .count();
    }

    private Score getOneAceScore() {
        if (isBust(totalScore(11))) {
            return new Score(totalScore(1));
        }
        return new Score(totalScore(11));
    }

    private boolean isBust(int score) {
        return score > 21;
    }

    public boolean isBust() {
        return score().isBust();
    }

    private int totalScore(int aceValue) {
        int score = 0;
        for (String s : cards) {
            String value = s.substring(0, 1);
            score += number(value, aceValue);
        }
        return score;
    }

    private int number(String value, int aceValue) {
        if (value.equals("J") || value.equals("Q") || value.equals("K")) {
            return 10;
        }
        if (value.equals("A")) {
            return aceValue;
        }
        return Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
