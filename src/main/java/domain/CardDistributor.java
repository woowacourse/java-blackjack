package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDistributor {

    private static final String CARD_RUN_OUT_ERROR_MESSAGE = "모든 참여자에게 카드를 분배할 수 없습니다.";
    private static final int INITIAL_CARD_SIZE = 2;

    private final List<Card> cards;

    public CardDistributor(List<Card> cards) {
        this.cards = cards;
        shuffle(cards);
    }

    private void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }

    public Card distribute() {
        return cards.remove(cards.size() - 1);
    }

    public CardDeck distributeInitialCard() {
        if (cards.size() < INITIAL_CARD_SIZE) {
            throw new IllegalArgumentException(CARD_RUN_OUT_ERROR_MESSAGE);
        }
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INITIAL_CARD_SIZE; i++) {
            cards.add(distribute());
        }
        return new CardDeck(cards);
    }

    public int getDeckSize() {
        return cards.size();
    }

    public boolean isCardLeft() {
        return cards.size() != 0;
    }

}
