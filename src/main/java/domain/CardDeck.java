package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.Arrays;

public class CardDeck {

    private final Cards cards;

    public CardDeck() {
        this.cards = initialize();
    }

    private Cards initialize() {
        Cards cards = new Cards();
        Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values())
                        .map(suit -> new Card(rank, suit)))
                .forEach(cards::addCard);
        cards.shuffle();
        return cards;
    }

    public Card pickCard() {
        return cards.pickCard();
    }
}
