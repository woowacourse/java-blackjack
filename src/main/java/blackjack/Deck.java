package blackjack;

import blackjack.common.ErrorMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Deck {
    private static final int DECK_SIZE = 52;
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        validate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validate(List<Card> cards) {
        // 덱에서 개수에 대한 검증을 스스로 해야 할지? Generator에게 책임을 위임시켜야할지
        // 테스트 할 때의 불편함이 있음

        if (cards.size() != DECK_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DECK_SIZE.getMessage());
        }

        if (new HashSet<>(cards).size() != DECK_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_CARD_EXISTED.getMessage());
        }

    }

    public void shuffle() {

    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> takeCards(int size) {
        validateEmpty(size);

        return IntStream.range(0, size)
                .mapToObj(i -> cards.removeLast()).toList();
    }

    private void validateEmpty(int size) {
        if (cards.size() < size) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_DECK_SIZE.getMessage());
        }
    }
}
