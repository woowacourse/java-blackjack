package model.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int ACE_ADDITIONAL_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = List.copyOf(cards);
    }

    public int calculateTotalNumbers() {
        int totalScore = totalScore();
        if (hasAce() && canAddAceScore(totalScore)) {
            return totalScore + ACE_ADDITIONAL_SCORE;
        }
        return totalScore;
    }

    private int totalScore() {
        return cards.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private boolean canAddAceScore(int totalScore) {
        return totalScore + ACE_ADDITIONAL_SCORE <= BLACKJACK_SCORE;
    }

    public Cards add(Card card) {
        List<Card> addedCards = new ArrayList<>(cards);
        addedCards.add(card);
        return new Cards(addedCards);
    }

    public Cards addAll(List<Card> cardsElement) {
        List<Card> addedCards = new ArrayList<>(cards);
        addedCards.addAll(cardsElement);
        return new Cards(addedCards);
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
