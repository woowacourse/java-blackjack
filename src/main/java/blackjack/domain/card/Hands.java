package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hands {

    public static final int INITIAL_HANDS_SIZE = 2;
    private final Cards cards;

    public Hands() {
        cards = Cards.of(new ArrayList<>());
    }

    public void initialize(List<Card> initialCards) {
        validate(initialCards);
        cards.addAll(initialCards);
    }

    private void validate(List<Card> initialCards) {
        if (initialCards.size() != INITIAL_HANDS_SIZE) {
            throw new IllegalArgumentException("[ERROR] 초기 카드는 2장입니다.");
        }
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculate() {
        return cards.calculate();
    }

    public List<Card> toList() {
        return cards.toList();
    }

    public List<Card> getCardsWithSize(int number) {
        return cards.getCardsWithSize(number);
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean containsAce() {
        return cards.containsAce();
    }
}
