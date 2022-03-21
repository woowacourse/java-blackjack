package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Deck {
    private static final String EMPTY_MESSAGE = "[ERROR] 덱의 카드가 다 소진되었습니다.";
    private static final int REMOVE_INDEX = 0;

    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        Stream.of(CardDenomination.values())
            .forEach(cardLetter -> Stream.of(CardSuit.values())
                .forEach(type -> cards.add(new Card(cardLetter, type)))
            );
    }

    private Card distributeCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(EMPTY_MESSAGE);
        }
        return cards.remove(REMOVE_INDEX);
    }

    public int getCardsSize() {
        return cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public List<Card> distributeCards(int amount) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            cards.add(distributeCard());
        }
        return cards;
    }
}
