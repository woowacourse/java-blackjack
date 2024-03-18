package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {

    private static final int DECK_TOP_POSITION = 0;
    private List<Card> deck;

    public CardDeck() {
        deck = readyCards();
        shuffleCard();
    }

    private List<Card> readyCards() {
        return Stream.of(CardPattern.values())
                .flatMap(this::createCardsForPattern)
                .collect(Collectors.toList());
    }

    private Stream<Card> createCardsForPattern(CardPattern pattern) {
        return Stream.of(CardNumber.values())
                .map(number -> new Card(number, pattern));
    }

    private void shuffleCard() {
        Collections.shuffle(deck);
    }

    public List<Card> firstCards() {
        List<Card> pickedCards = new ArrayList<>();

        pickedCards.add(pickCard());
        pickedCards.add(pickCard());

        return pickedCards;
    }

    public Card pickCard() {
        return deck.remove(DECK_TOP_POSITION);
    }

    public int getDeckSize() {
        return deck.size();
    }
}
