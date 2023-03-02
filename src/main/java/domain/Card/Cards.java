package domain.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Cards {
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
            throw new IllegalArgumentException("[ERROR] 카드가 존재하지 않습니다.");
        }
        return cards.remove(0);
    }

    public List<Card> drawForFirstTurn() {
        List<Card> firstTurnCards = new ArrayList<>();
        firstTurnCards.add(drawCard());
        firstTurnCards.add(drawCard());

        return firstTurnCards;
    }

    private boolean isEmpty() {
        return cards.isEmpty();
    }
}
