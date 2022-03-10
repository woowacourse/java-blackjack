package blackjack.cards;

import static java.util.function.Predicate.not;
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
}
