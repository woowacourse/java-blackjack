package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer implements Person {
    private static final int DEALER_STOP_HIT_BOUND = 17;
    private static final int BURST_NUMBER = 21;
    private static final int DIFFERENCE_WITH_ACE_NUMBER = 10;
    private final List<Card> cards;

    public Dealer() {
        this.cards = new ArrayList<>();
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public boolean isHit() {
        int totalScore = calculateScore();
        return totalScore < DEALER_STOP_HIT_BOUND;
    }

    @Override
    public int calculateScore() {
        int totalScore = cards.stream()
                .mapToInt(card -> card.getScore(card.getCardNumberToString()))
                .sum();

        if (totalScore > BURST_NUMBER && hasACE()) {
            return totalScore - DIFFERENCE_WITH_ACE_NUMBER;
        }
        return totalScore;
    }

    private boolean hasACE() {
        return cards.stream()
                .anyMatch(Card::isACE);
    }

    @Override
    public List<Card> showCards() {
        return List.of(cards.get(0));
    }

    public List<Card> showAllCards() {
        return Collections.unmodifiableList(cards);
    }
}
