package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Cards {

    private static final int ACE_ADDITION = 10;
    private static final int MAX_SCORE = 21;
    private static final int CARD_COUNT_FOR_BLACKJACK = 2;
    private static final List<Card> EMPTY_LIST = Collections.emptyList();

    private final List<Card> cards;

    private Cards(List<Card> initialCards) {
        cards = new ArrayList<>(initialCards);
    }

    public static Cards of(List<Card> cards) {
        return new Cards(cards);
    }

    public static Cards getEmptyCardsPack() {
        return new Cards(EMPTY_LIST);
    }

    public int calculateScore() {
        int score = calculateSum();

        if (isContainAce()) {
            return calculateScoreWithAce(score);
        }
        return score;
    }

    public int calculateSum() {
        return cards.stream()
            .mapToInt(Card::getScore)
            .sum();
    }

    private boolean isContainAce() {
        return cards.stream()
            .anyMatch(Card::isAceCard);
    }

    private int calculateScoreWithAce(int score) {
        if (score + ACE_ADDITION <= MAX_SCORE) {
            return score + ACE_ADDITION;
        }
        return score;
    }

    public boolean isBlackJack() {
        return cards.size() == CARD_COUNT_FOR_BLACKJACK && calculateScore() == MAX_SCORE;
    }

    public boolean isBust() {
        return calculateScore() > MAX_SCORE;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public Card getCardByIndex(int index) {
        return cards.get(index);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
