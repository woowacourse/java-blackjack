package blackjack.model;

import static java.util.function.Predicate.not;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Cards {

    public static final int ACE_WITH_RANK_ELEVEN = 11;
    public static final int ACE_WITH_RANK_ONE = 1;

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

    private Score scoreByAceWithRankOne() {
        int score = cards.stream()
                .mapToInt(Card::hardRank)
                .sum();
        return new Score(score);
    }

    public Score bestScore() {
        return applyAceWithRankEleven()
                .filter(not(Score::isBust))
                .orElse(scoreByAceWithRankOne());
    }

    private Optional<Score> applyAceWithRankEleven() {
        if (hasAce()) {
            return Optional.of(scoreByAceWithRankOne().plus(diffBetweenRankOfAce()));
        }
        return Optional.empty();
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    private Score diffBetweenRankOfAce() {
        return new Score(ACE_WITH_RANK_ELEVEN - ACE_WITH_RANK_ONE);
    }
}
