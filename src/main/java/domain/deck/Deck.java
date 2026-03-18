package domain.deck;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Rank;
import domain.card.Suit;

import java.util.*;
import java.util.stream.Collectors;

import static domain.game.BlackjackRule.INITIAL_CARDS_COUNT;

public class Deck {
    private final Cards cards;

    public Deck(List<Card> cards) {
        this.cards = new Cards(cards);
    }

    public static Deck createDeck(CardShuffleStrategy cardShuffleStrategy) {
        List<Card> beginningCards = Arrays.stream(Rank.values())
                .flatMap(
                        rank -> Arrays.stream(Suit.values())
                                .map(suit -> new Card(rank, suit))
                )
                .collect(Collectors.toCollection(ArrayList::new));

        List<Card> cards = cardShuffleStrategy.shuffle(beginningCards);

        return new Deck(cards);
    }

    public Card draw() {
        return cards.draw();
    }

    public Cards drawInitialCards() {
        Cards initCards = new Cards();
        for (int i = 0; i < INITIAL_CARDS_COUNT; i++) {
            initCards.add(draw());
        }

        return initCards;
    }
}
