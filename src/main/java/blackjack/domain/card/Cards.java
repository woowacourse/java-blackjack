package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Cards {
    private static final String DUPLICATION_CARDS = "중복된 카드는 보유할 수 없습니다.";

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        validateCardDuplication(cards);
        this.cards = cards;
    }

    public Cards() {
        this(new ArrayList<>());
    }

    private void validateCardDuplication(List<Card> cards) {
        int cardCounts = cards.size();
        int distinctCardCounts = new HashSet<>(cards).size();
        if (cardCounts != distinctCardCounts) {
            throw new IllegalArgumentException(DUPLICATION_CARDS);
        }
    }

    public void add(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(DUPLICATION_CARDS);
        }
        cards.add(card);
    }

    public void addAll(Cards targetCards) {
        if (cards.containsAll(targetCards.cards)) {
            throw new IllegalArgumentException(DUPLICATION_CARDS);
        }
        cards.addAll(targetCards.cards);
    }

    public int calculateFinalScore() {
        int score = calculateScoreWhenAceIsMinimum();
        int aceCounts = calculateAceCounts();
        int aceBonusScore = Symbol.ACE.calculateAceBonusScore(score, aceCounts);
        return score + aceBonusScore;
    }

    public int calculateScoreWhenAceIsMinimum() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int calculateAceCounts() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
