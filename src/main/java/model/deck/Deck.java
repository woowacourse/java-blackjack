package model.deck;

import model.card.Card;
import model.card.CardFactory;
import model.cards.Cards;
import model.dice.Dice;
import model.dice.DiceImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {

    private static Deck instance;

    private final Cards cards;

    private Deck() {
        this.cards = CardFactory.createCards();
    }

    public static Deck getInstance() {
        if (isNotCreated()) {
            createNewInstance();
        }
        return instance;
    }

    private static boolean isNotCreated() {
        return instance == null;
    }

    private static void createNewInstance() {
        synchronized (Deck.class) {
            instance = new Deck();
        }
    }

    public List<Card> getCardsFromDeckAsMuchAs(int count) {
        return IntStream.range(0, count)
                .mapToObj(x -> getRandomCardFromDeck(new DiceImpl()))
                .collect(Collectors.toList());
    }

    private Card getRandomCardFromDeck(final Dice dice) {
        int index = dice.getRandomIndex();
        return cards.useCard(index);
    }
}
