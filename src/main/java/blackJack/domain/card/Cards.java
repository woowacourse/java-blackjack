package blackJack.domain.card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cards {

    private static final int ACE_BONUS_SCORE = 10;

    private final Set<Card> cards;

    public Cards() {
        cards = new HashSet<>();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackJack() {
        final Score score = new Score(calculateScore());
        return score.isBlackJack(cards.size());
    }

    public boolean isBust() {
        final Score score = new Score(calculateScore());
        return score.isBust();
    }

    public int calculateScore() {
        final int sumScore = calculateCardsSum();
        final Score score = new Score(sumScore);
        if (hasAce(cards) && score.hasPossibleAcePoint()) {
            return sumScore + ACE_BONUS_SCORE;
        }
        return sumScore;
    }

    private boolean hasAce(Set<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int calculateCardsSum() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public List<Card> getCards() {
        return cards.stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
