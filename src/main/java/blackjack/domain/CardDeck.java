package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private static final int BLACKJACK_CARD_COUNT = 2;
    private final List<Card> cards;

    public CardDeck() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        validateNull(card);
        cards.add(card);
    }

    private void validateNull(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("아무런 카드도 입력되지 않았습니다.");
        }
    }

    public Score calculateScore() {
        Score totalScore = new Score(sumStandardScore());
        int aceCount = findAceCount();
        if (aceCount > 0) {
            return totalScore.addAceScore(aceCount);
        }
        return totalScore;
    }

    private int sumStandardScore() {
        return cards.stream().filter(card -> !card.isAce()).mapToInt(Card::getValue).sum();
    }

    private int findAceCount() {
        return (int) cards.stream()
            .filter(Card::isAce)
            .count();
    }

    public boolean isBust() {
        return calculateScore().isBust();
    }

    public boolean isBlackJack() {
        Score score = calculateScore();
        int count = cards.size();
        return score.isBlackJackScore() && count == BLACKJACK_CARD_COUNT;
    }

    public boolean isEqualScore(CardDeck deck) {
        return calculateScore().equals(deck.calculateScore());
    }

    public boolean isMoreThan(CardDeck deck) {
        return calculateScore().isMoreThan(deck.calculateScore());
    }

    public List<Card> getCards() {
        return cards;
    }
}
