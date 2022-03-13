package blackjack.model;

import static java.util.function.Predicate.not;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Cards {

    public static final int SOFT_HAND_ACE = 11;
    public static final int HARD_HAND_ACE = 1;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public List<Card> getEachCard() {
        return cards;
    }

    public void takeCard(Card card) {
        cards.add(card);
    }

    public Score softHandScore() {
        int score = cards.stream()
                .mapToInt(Card::softRank)
                .sum();
        return new Score(score);
    }

    public Score bestScore() {
        return applySoftHandScore()
                .filter(not(Score::isBust))
                .orElse(hardHandScore());
    }

    private Score hardHandScore() {
        int score = cards.stream()
                .mapToInt(Card::hardRank)
                .sum();
        return new Score(score);
    }

    private Optional<Score> applySoftHandScore() {
        if (hasAce()) {
            return Optional.of(hardHandScore().plus(diffSoftAndHardOfAce()));
        }
        return Optional.empty();
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    private Score diffSoftAndHardOfAce() {
        return new Score(SOFT_HAND_ACE - HARD_HAND_ACE);
    }
}
