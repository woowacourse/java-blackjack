package model;

import exception.EmptyDeckException;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static controller.BlackJackGame.ADDITIONAL_DRAW_COUNT;

public class Deck {
    private final Stack<Card> cards = new Stack<>();

    public Deck(List<Card> cards) {
        this.cards.addAll(cards);
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        validateSize();
        return cards.pop();
    }

    public int getCurrentDeckSize() {
        return cards.size();
    }

    private void validateSize() {
        if (getCurrentDeckSize() - ADDITIONAL_DRAW_COUNT < 0) {
            throw new EmptyDeckException("Deck에 카드가 존재하지 않습니다.");
        }
    }

}
