package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cards {
    public static final int BLACKJACK_SCORE = 21;
    private static final int FIRST_CARD_INDEX = 0;
    private static final int ZERO = 0;
    private static final int GAP_BETWEEN_ACE_SCORE = 10;

    private final List<Card> cards;

    public Cards() {
        this(new ArrayList<>());
    }

    public Cards(List<Card> deck) {
        this.cards = new ArrayList<>(deck);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card getFirstCard() {
        return this.cards.get(FIRST_CARD_INDEX);
    }

    public String getCardsInformation() {
        return cards.stream()
                .map(Card::getCardInformation)
                .collect(Collectors.joining(", "));
    }

    public int calculateScore() {
        int totalScore = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        return calculateIncludingAce(totalScore);
    }

    private int calculateIncludingAce(int totalScore) {
        int aceCount = countNumberOfAce();
        totalScore = calculateFinalScore(totalScore, aceCount);
        return totalScore;
    }

    private int countNumberOfAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int calculateFinalScore(int totalScore, int aceCount) {
        while (aceCount != ZERO && totalScore + GAP_BETWEEN_ACE_SCORE <= BLACKJACK_SCORE) {
            totalScore += GAP_BETWEEN_ACE_SCORE;
            aceCount--;
        }
        return totalScore;
    }

    public int size() {
        return this.cards.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

}
