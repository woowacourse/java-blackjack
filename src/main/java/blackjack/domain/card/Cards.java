package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int ACE_BONUS_VALUE = 10;
    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Cards add(final Card card) {
        List<Card> newCardList = new ArrayList<>(cards);
        newCardList.add(card);
        return new Cards(newCardList);
    }

    public boolean isBust() {
        return sumCardValue() > 21;
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && containAce() && sumCardValue() == 11;
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }

    public Score getScore() {
        final int cardSum = sumCardValue();
        if (containAce() && cardSum + ACE_BONUS_VALUE <= Score.MAX_SCORE_VALUE) {
            return new Score(cardSum + ACE_BONUS_VALUE);
        }
        return new Score(cardSum);
    }

    private boolean containAce() {
        return cards.stream()
                .anyMatch((card) -> card.isAce());
    }

    private int sumCardValue() {
        return cards.stream()
                .map(Card::getCardNUmberValue)
                .reduce(Integer::sum)
                .orElseGet(() -> 0);
    }

}
