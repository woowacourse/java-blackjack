package domain;

import domain.shuffle.CardShuffleStrategy;
import view.mesage.ErrorMessage;

import static java.util.Arrays.*;

import java.util.stream.Stream;

public class Deck {
    private final Cards cards;

    private Deck(CardShuffleStrategy shuffleStrategy) {
        this.cards = generateCards();
        shuffleStrategy.shuffle(cards);
    }

    public static Deck of(CardShuffleStrategy shuffleStrategy) {
        return new Deck(shuffleStrategy);
    }

    private Cards generateCards() {
        return Cards.of(stream(Rank.values())
                .flatMap(rank -> stream(Suit.values())
                        .map(suit -> Card.of(rank, suit)))
                .toList());
    }

    public Cards drawInitialHand() {
        Cards cards = Cards.of(Stream.generate(this::draw)
                .limit(Policy.FIRST_DRAW_SIZE)
                .toList());
        return cards;
    }

    public Card draw() {
        validateDeckDrawing(cards);
        return cards.removeFirst();
    }

    private void validateDeckDrawing(Cards cards) {
        if (cards.size() == 0){
            throw new IllegalArgumentException(ErrorMessage.EMPTY_DECK.getMessage());
        }
    }
}
