package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardHand {

    private static final int BLACK_JACK = 21;
    private static final int BLACK_JACK_CARD_COUNT = 2;
    private static final int BUST_SCORE_THRESHOLD = 21;

    private final List<Card> cards = new ArrayList<>();

    public void takeCards(Card... cards) {
        this.cards.addAll(Arrays.asList(cards));
    }

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_CARD_COUNT
            && calculateScore() == BLACK_JACK;
    }

    public boolean isBust() {
        return calculateScore() > BUST_SCORE_THRESHOLD;
    }

    public int calculateScore() {
        int sum = cards.stream()
            .mapToInt(Card::getNumber)
            .sum();

        if (hasAce()) {
            return calculateOptimalScore(sum);
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream()
            .anyMatch(card -> card.getRank() == Rank.ACE);
    }

    private int calculateOptimalScore(int sum) {
        if (sum + Rank.ACE_LOW_HIGH_GAP <= BLACK_JACK) {
            return sum + Rank.ACE_LOW_HIGH_GAP;
        }
        return sum;
    }

    public Card getFirst() {
        return cards.getFirst();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
