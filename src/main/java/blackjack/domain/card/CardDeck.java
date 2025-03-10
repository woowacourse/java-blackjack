package blackjack.domain.card;

import blackjack.exception.ExceptionMessage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private Queue<Card> cards;

    public CardDeck() {
        List<Card> shuffledCards = makeShuffledCards();
        this.cards = new ArrayDeque<>();
        this.cards.addAll(shuffledCards);
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> drawCard(int count) {
        validateEmptyCardDeck(count);
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            drawnCards.add(cards.poll());
        }
        return drawnCards;
    }

    private List<Card> makeShuffledCards() {
        List<Card> newCards = new ArrayList<>();
        for (CardShape shape : CardShape.values()) {
            Arrays.stream(CardValue.values())
                    .map(cardValue -> new Card(shape, cardValue))
                    .forEach(newCards::add);
        }
        Collections.shuffle(newCards);
        return newCards;
    }

    private void validateEmptyCardDeck(int count) {
        if (cards.size() < count) {
            throw new IllegalArgumentException(ExceptionMessage.EMPTY_CARD_DECK.getContent());
        }
    }
}
