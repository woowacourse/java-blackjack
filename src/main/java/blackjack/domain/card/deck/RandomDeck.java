package blackjack.domain.card.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomDeck implements Deck {

    private List<Card> cards;

    public RandomDeck() {
        init();
    }

    @Override
    public Card pick() {
        if (cards.isEmpty()) {
            init();
        }
        Collections.shuffle(cards);
        return cards.remove(cards.size() - 1);
    }

    @Override
    public List<Card> pickTwoCards() {
        if (cards.isEmpty()) {
            init();
        }
        Collections.shuffle(cards);
        List<Card> newCards = new ArrayList<>();
        newCards.add(cards.remove(cards.size() - 1));
        newCards.add(cards.remove(cards.size() - 1));

        return newCards;
    }

    private void init() {
        cards = new ArrayList<>();
        for (CardNumber number : CardNumber.values()) {
            Arrays.stream(CardType.values())
                    .forEach(type -> cards.add(new Card(number, type)));
        }
    }
}
