package blackjack.domain.card.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RandomDeck implements Deck {

    private Queue<Card> cards;

    public RandomDeck() {
        init();
    }

    @Override
    public Card pick() {
        if (cards.isEmpty()) {
            init();
        }
        return cards.poll();
    }

    @Override
    public List<Card> pickTwoCards() {
        if (cards.isEmpty()) {
            init();
        }
        List<Card> newCards = new ArrayList<>();

        newCards.add(cards.poll());
        newCards.add(cards.poll());

        return newCards;
    }

    private void init() {
        List<Card> deck = new LinkedList<>();
        for (CardNumber number : CardNumber.values()) {
            Arrays.stream(CardType.values())
                    .forEach(type -> deck.add(Card.of(number, type)));
        }
        Collections.shuffle(deck);
        this.cards = new LinkedList<>(deck);
    }
}
