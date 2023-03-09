package domain.card;

import domain.player.Participant;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class CardDeck {

    private final Deque<Card> cards;

    private CardDeck(final List<Card> cards) {
        this.cards = new ArrayDeque<>(cards);
    }

    public static CardDeck shuffledFullCardDeck(final CardShuffler cardShuffler) {
        final List<Card> cards = makeTotalCards();
        final List<Card> shuffled = cardShuffler.shuffle(cards);
        return new CardDeck(shuffled);
    }

    private static List<Card> makeTotalCards() {
        return stream(CardShape.values())
                .flatMap(CardDeck::makeCardCorrespondsShape)
                .collect(toList());
    }

    private static Stream<Card> makeCardCorrespondsShape(final CardShape shape) {
        return stream(CardValue.values())
                .map(value -> new Card(shape, value));
    }

    public CardArea createCardArea() {
        return new CardArea(draw(), draw());
    }

    public List<Card> cards() {
        return new ArrayList<>(cards);
    }

    private Card draw() {
        return this.cards.pollFirst();
    }

    public void drawTo(final Participant participant) {
        participant.hit(draw());
    }
}
