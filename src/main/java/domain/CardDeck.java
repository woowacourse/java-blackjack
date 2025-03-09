package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<TrumpCard> CARD_DECK = new ArrayList<>();

    public CardDeck() {
        Arrays.stream(CardShape.values())
                .forEach(cardShape -> Arrays.stream(CardNumber.values())
                        .forEach(cardNumber -> CARD_DECK.add(new TrumpCard(cardShape, cardNumber))));
        Collections.shuffle(CARD_DECK);
    }

    public TrumpCard drawCard() {
        if (CARD_DECK.isEmpty()) {
            throw new IllegalArgumentException("카드가 다 떨어졌습니다");
        }
        return CARD_DECK.removeFirst();
    }

    public TrumpCard getCard(int index) {
        return CARD_DECK.get(index);
    }

    public List<TrumpCard> getCardDeck() {
        return new ArrayList<>(CARD_DECK);
    }
}
