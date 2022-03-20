package model.card;

import java.util.List;
import model.Status;
import model.card.cardGettable.CardsGettable;

public class Cards {
    public static final int BLACK_JACK_SCORE = 21;
    private static final int SCORE_GAP_PER_ACE = 10;
    private static final String DUPLICATED_CARD_MESSAGE = "중복된 카드를 받을 수 없습니다.";

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        if (isDuplicated(cards)) {
            throw new IllegalArgumentException(DUPLICATED_CARD_MESSAGE);
        }
        this.cards = cards;
    }

    private boolean isDuplicated(final List<Card> cards) {
        return cards.stream().distinct().count() != cards.size();
    }

    public int getSum() {
        if (countAce() == 0) {
            return getMinimumSum();
        }
        return (convertableAceCount() * SCORE_GAP_PER_ACE) + getMinimumSum();
    }

    private int convertableAceCount() {
        return Math.min((BLACK_JACK_SCORE - getMinimumSum()) / SCORE_GAP_PER_ACE, countAce());
    }

    private int getMinimumSum() {
        return cards.stream()
                .mapToInt(Card::getCardScore)
                .sum();
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAceCard)
                .count();
    }

    public void addCard(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(DUPLICATED_CARD_MESSAGE);
        }
        cards.add(card);
    }

    public Status getStatus() {
        return Status.of(cards.size(), getSum());
    }

    public List<Card> getCardsByStrategy(CardsGettable strategy) {
        return strategy.getCards(cards);
    }
}
