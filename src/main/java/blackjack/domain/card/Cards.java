package blackjack.domain.card;

import java.util.*;

public class Cards {
    private static final int BLACKJACK_CARD_COUNTS = 2;
    private static final int BLACKJACK_CARD_SCORE = 21;
    private static final String DUPLICATION_CARDS = "중복된 카드는 보유할 수 없습니다.";

    private final Set<Card> cards;

    public Cards(List<Card> cards) {
        validateCardDuplication(cards);
        this.cards = new LinkedHashSet<>(cards);
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
        boolean isUniqueCardAdded = cards.add(card);
        validateCardDuplication(isUniqueCardAdded);
    }

    private void validateCardDuplication(boolean isUniqueCardAdded) {
        if (!isUniqueCardAdded) {
            throw new IllegalArgumentException(DUPLICATION_CARDS);
        }
    }

    public void addAll(Cards targetCards) {
        boolean isUniqueCardAdded = cards.addAll(targetCards.cards);
        validateCardDuplication(isUniqueCardAdded);
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

    public boolean isBlackJack() {
        return cards.size() == BLACKJACK_CARD_COUNTS && calculateFinalScore() == BLACKJACK_CARD_SCORE;
    }

    public boolean isBust() {
        return calculateFinalScore() > BLACKJACK_CARD_SCORE;
    }

    public Set<Card> getCards() {
        return Collections.unmodifiableSet(cards);
    }
}
