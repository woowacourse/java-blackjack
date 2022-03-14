package blackJack.domain.card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cards {
    private static final int BLACK_JACK_CARD_COUNT = 2;
    private static final int BLACK_JACK = 21;
    private static final int OTHER_SCORE_OF_ACE_DENOMINATION = 11;

    private final Set<Card> cards;

    public Cards() {
        cards = new HashSet<>();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_CARD_COUNT && calculateFinalScore() == BLACK_JACK;
    }

    public boolean isBust() {
        return calculateFinalScore() > BLACK_JACK;
    }

    public int calculateFinalScore() {
        final int score = calculateScore();
        if (hasAce() && score + OTHER_SCORE_OF_ACE_DENOMINATION - Denomination.ACE.getScore() <= BLACK_JACK) {
            return score + OTHER_SCORE_OF_ACE_DENOMINATION - Denomination.ACE.getScore();
        }
        return score;
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public List<Card> getCards() {
        return cards.stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
