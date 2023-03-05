package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import view.ErrorMessage;

public class Cards {
    private static final int CARD_ON_TOP_INDEX = 0;

    private final List<Card> cards = new ArrayList<>();

    public Cards() {
        addAllCards();
        Collections.shuffle(cards);
    }

    private void addAllCards() {
        cards.addAll(Arrays.asList(CloverCard.values()));
        cards.addAll(Arrays.asList(DiamondCard.values()));
        cards.addAll(Arrays.asList(HeartCard.values()));
        cards.addAll(Arrays.asList(SpadeCard.values()));
    }

    public Card drawCard() {
        if (isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.NO_MORE_CARD.getMessage());
        }
        return cards.remove(CARD_ON_TOP_INDEX);
    }

    public List<Card> drawTwoCards() {
        List<Card> firstTurnCards = new ArrayList<>();
        firstTurnCards.add(drawCard());
        firstTurnCards.add(drawCard());

        return firstTurnCards;
    }

    private boolean isEmpty() {
        return cards.isEmpty();
    }
}
