package blackjack.cards;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.Card;
import blackjack.Rank;
import blackjack.Score;
import java.util.List;
import java.util.stream.IntStream;

final class MixHandCards extends ChangeableCards {

    MixHandCards(HardHandCards cards) {
        super(cards);
    }

    public Score score() {
        return possibleScores().stream()
                .filter(not(Score::isBust))
                .reduce(this::bestScore)
                .orElse(hardHandScore());
    }

    private List<Score> possibleScores() {
        return softHands().stream()
                .map(Score::new)
                .collect(toUnmodifiableList());
    }

    private List<Integer> softHands() {
        return IntStream.rangeClosed(1, numberOfAce())
                .map(value -> diffSoftAndHard(Rank.ACE) * value + hardHandScore().getValue())
                .boxed()
                .collect(toList());
    }

    private int diffSoftAndHard(Rank rank) {
        return rank.soft() - rank.hard();
    }

    private int numberOfAce() {
        return (int) stream()
                .filter(Card::isAce)
                .count();
    }

    private Score bestScore(Score score1, Score score2) {
        if (score1.moreThan(score2)) {
            return score1;
        }
        return score2;
    }
}
