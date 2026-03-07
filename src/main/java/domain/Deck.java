package domain;

import java.util.HashSet;
import java.util.List;

import util.ErrorMessage;

public class Deck {
    public static final int CARD_SIZE = 52;

    private final List<Card> cards;

    public Deck(List<Card> cards) {
        validateSize(cards);
        validateDuplicate(cards);
        this.cards = cards;
    }

    private void validateSize(List<Card> cards) {
        if (cards.size() != CARD_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.DECK_SIZE.getMessage());
        }
    }

    private void validateDuplicate(List<Card> cards) {
        if (new HashSet<>(cards).size() != cards.size()) {
            throw new IllegalArgumentException(ErrorMessage.DECK_DUPLICATE.getMessage());
        }
    }

    public Card drawCard(int idx) {
        if (idx < 0 || idx > cards.size()) {
            throw new IllegalArgumentException(ErrorMessage.INDEX_RANGE.getMessage());
        }

        Card card = cards.get(idx);
        cards.remove(idx);

        return card;
    }

    public int getSize(){
        return cards.size();
    }

    public boolean contains(Card card){
        return cards.contains(card);
    }



}
