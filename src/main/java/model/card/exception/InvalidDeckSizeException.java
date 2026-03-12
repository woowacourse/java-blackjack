package model.card.exception;

import static model.card.RandomDeck.REQUIRED_DECK_SIZE;

public class InvalidDeckSizeException extends IllegalArgumentException {
    public InvalidDeckSizeException(int deckSize) {
        super("덱은 " + REQUIRED_DECK_SIZE + "개의 카드를 가져야 합니다(현재 " + deckSize + "개).");
    }
}
