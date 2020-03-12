package model;

import exception.EmptyDeckException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    public static final int INITIAL_DRAW_COUNT = 2;
    private List<Card> cards = new ArrayList<>();

    public Deck(List<Card> cards) {
        this.cards.addAll(cards);
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

//    public CardHand initialDraw() {
//        validateSize();
//        CardHand cardHand = new CardHand();
//        for (int i = 0; i < INITIAL_DRAW_COUNT; i++) {
//            cardHand.addCard(draw());
//        }
//        return cardHand;
//    }

    public CardHand draw(int count) {
        validateSize(count);
        CardHand cardHand = new CardHand();
        for (int i = 0; i < count; i++) {
            int lastIndex = getCurrentDeckSize() - 1;
            Card card = cards.get(lastIndex);
            cardHand.addCard(card);
            cards.remove(lastIndex);
        }
        return cardHand;
    }

    public int getCurrentDeckSize() {
        return cards.size();
    }

    private void validateSize(int count) {
        if (getCurrentDeckSize() - count < 0) {
            throw new EmptyDeckException(count + "장 이상 draw 할 카드가 존재하지 않습니다.");
        }
    }

}
