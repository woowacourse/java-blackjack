package blackjack;

import java.util.List;

public class Cards {

    private final List<String> cards;

    public Cards(String... cards) {
        this.cards = List.of(cards);
    }

    public Score score() {
        if (hasMoreThanTwoAce()) {
            List<Integer> temp = getPossibleScore();
            return new Score(temp.stream()
                    .filter(tmp -> !isBust(tmp))
                    .mapToInt(Integer::intValue)
                    .max().getAsInt());

        }
        return getOneAceScore();
    }

    private List<Integer> getPossibleScore() {
        return null;
    }

    private boolean hasMoreThanTwoAce() {
        return cards.stream()
                .map(s -> s.substring(0, 1))
                .filter(s -> s.equals("A"))
                .count() >= 2;
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
