package model;

import exception.EmptyDeckException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck implements Strategy {
    public static final int initialDrawCount = 2;
    public static final int INITIAL_DRAW_COUNT = initialDrawCount;
    private List<Card> cards = new ArrayList<>();

    public Deck(List<Card> cards) {
        this.cards.addAll(cards);
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public Card draw() {
        validateSize();
        int lastIndex = cards.size() - 1;
        Card drawCard = cards.get(lastIndex);
        cards.remove(lastIndex);
        return drawCard;
    }

    @Override
    public CardHand initialDraw() {
        validateSize();
        CardHand cardHand = new CardHand();
        for (int i = 0; i < INITIAL_DRAW_COUNT; i++) {
            cardHand.addCard(draw());
        }
        return cardHand;
    }

    private void validateSize() {
        if (cards.size() <= 0) {
            throw new EmptyDeckException("더 이상 draw 할 카드가 존재하지 않습니다.");
        }
    }

}
