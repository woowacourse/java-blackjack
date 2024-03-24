package blackjackgame.model.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
                .mapToInt(Card::score)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cards cardsForCompare = (Cards) o;
        Map<Card, Integer> cardsCount = cardsForCompare.toHashMap();
        return cardsCount.equals(this.toHashMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    private Map<Card, Integer> toHashMap() {
        Map<Card, Integer> cardCount = new HashMap<>();
        for (Card card : cards) {
            cardCount.putIfAbsent(card, 1);
            cardCount.computeIfPresent(card, (k, v) -> v + 1);
        }
        return cardCount;
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
