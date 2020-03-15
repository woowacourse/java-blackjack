package model;

import exception.EmptyDeckException;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards = new Stack<>();

    public Deck(List<Card> cards) {
        this.cards.addAll(cards);
        shuffle();
    }

    public Deck(CardHand cardHand){
        this.cards.addAll(cardHand.getCards());
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public CardHand draw(int count) {
        validateSize(count);
        CardHand cardHand = new CardHand();
        for (int i = 0; i < count; i++) {
            Card card = cards.pop();
            cardHand.addCard(card);
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
