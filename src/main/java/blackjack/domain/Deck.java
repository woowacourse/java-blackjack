package blackjack.domain;

import blackjack.common.ErrorMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Deck {

    private static final int STARTING_CARD_SIZE = 2;
    private static final int ADDITIONAL_CARD_SIZE = 1;

    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Deck shuffled(List<Card> cards) {
        ArrayList<Card> copiedCards = new ArrayList<>(cards);
        Collections.shuffle(copiedCards);

        return new Deck(copiedCards);
    }

    public List<Card> startingHand() {
        return takeCards(STARTING_CARD_SIZE);
    }

    private List<Card> takeCards(int size) {
        validateEmpty(size);

        return IntStream.range(0, size)
                .mapToObj(i -> cards.removeLast())
                .toList();
    }

    private void validateEmpty(int size) {
        if (cards.size() < size) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_DECK_SIZE.getMessage());
        }
    }

    public List<Card> hit() {
        return takeCards(ADDITIONAL_CARD_SIZE);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
