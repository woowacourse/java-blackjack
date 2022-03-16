package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeck {

    private static final String OVER_CAPACITY_EXCEPTION_MESSAGE = "카드가 부족합니다.";
    public static final int INITIAL_CARD_COUNT = 2;
    public static final int NORMAL_CARD_COUNT = 1;

    private final LinkedList<Card> cardDeck;

    public CardDeck(List<Card> cardDeck) {
        this.cardDeck = new LinkedList<>(cardDeck);
    }

    public static CardDeck initShuffled() {
        List<Card> cardDeck = Arrays.stream(Number.values())
                .flatMap(number ->
                        Arrays.stream(Kind.values())
                            .map(kind -> Card.from(number, kind)))
                .collect(Collectors.toList());
        Collections.shuffle(cardDeck);

        return new CardDeck(cardDeck);
    }

    public int leftSize() {
        return cardDeck.size();
    }

    public Cards startCards() {
        validateCapacity(INITIAL_CARD_COUNT);
        List<Card> distribution = new LinkedList<>();
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            distribution.add(cardDeck.pop());
        }
        return new Cards(distribution);
    }

    public Cards draw() {
        validateCapacity(NORMAL_CARD_COUNT);
        List<Card> distribution = new LinkedList<>();
        for (int i = 0; i < NORMAL_CARD_COUNT; i++) {
            distribution.add(cardDeck.pop());
        }
        return new Cards(distribution);
    }

    private void validateCapacity(int count) {
        if (cardDeck.size() < count) {
            throw new IllegalArgumentException(OVER_CAPACITY_EXCEPTION_MESSAGE);
        }
    }
}
