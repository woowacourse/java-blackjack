package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements Person {
    private static final int BURST_NUMBER = 21;
    private static final int ACE_MAX_NUMBER = 11;
    private static final int DIFFERENCE_WITH_ACE_NUMBER = 10;
    private final Name name;
    private final List<Card> cards;

    public Player(Name name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public List<Card> showCards() {
        return Collections.unmodifiableList(cards);
    }

    public String showName() {
        return name.getName();
    }

    @Override
    public boolean isHit() {
        int totalScore = calculateScore();
        return totalScore < BURST_NUMBER;
    }

    @Override
    public int calculateScore() {
        int totalScore = cards.stream()
                .mapToInt(card -> card.getScore(card.getCardNumberToString()))
                .sum();

        if (totalScore <= ACE_MAX_NUMBER && hasACE()) {
            return totalScore + DIFFERENCE_WITH_ACE_NUMBER;
        }
        return totalScore;
    }

    private boolean hasACE() {
        return cards.stream()
                .anyMatch(card -> card.getCardNumberToString().equals(CardNumber.ACE.number()));
    }
}
