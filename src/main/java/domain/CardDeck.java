package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public List<List<Card>> pickInitialCardsStack(int stackSize) {
        List<List<Card>> cardsStack = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Card> cards = pickInitialCards(stackSize);
            cardsStack.add(cards);
        }
        return cardsStack;
    }

    public Card pickCard() {
        return cards.pickCard();
    }

    private List<Card> pickInitialCards(int stackSize) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < stackSize; i++) {
            cards.add(pickCard());
        }
        return cards;
    }
}
