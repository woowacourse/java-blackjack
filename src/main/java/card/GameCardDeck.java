package card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GameCardDeck {
    private final List<Card> cards;

    private GameCardDeck(final List<Card> cards) {
        this.cards = cards;
    }

    public static GameCardDeck generateFullPlayingCard() {
        List<Card> initializePlayingCard = initializePlayingCard();
        return new GameCardDeck(initializePlayingCard);
    }

    private static List<Card> initializePlayingCard() {
        return Arrays.stream(CardNumber.values())
                .flatMap(cardNumber -> Arrays.stream(CardSymbol.values())
                        .map(cardSymbol -> new Card(cardNumber, cardSymbol)))
                .collect(Collectors.toList());
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public List<Card> draw(final int count) {
        List<Card> drawedCards = new ArrayList<>();
        for (int i = 0; i < count; i ++) {
            drawedCards.add(cards.removeFirst());
        }
        return drawedCards;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
