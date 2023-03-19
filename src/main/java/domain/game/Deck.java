package domain.game;

import domain.strategy.ShuffleStrategy;
import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardType;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private static final int TOP_CARD = 0;

    private static final List<Card> cards;

    private final ShuffleStrategy shuffleStrategy;

    static {
        cards = makeCards();
    }

    public Deck(final ShuffleStrategy shuffleStrategy) {
        this.shuffleStrategy = shuffleStrategy;
    }

    private static List<Card> makeCards() {
        List<Card> cardsOfAllCardType = new ArrayList<>();
        for (CardType cardType : CardType.values()) {
            cardsOfAllCardType.addAll(makeCardsOfOneCardType(cardType));
        }
        return cardsOfAllCardType;
    }

    private static List<Card> makeCardsOfOneCardType(final CardType cardType) {
        List<Card> cardsOfOneCardType = new ArrayList<>();
        for (CardNumber cardNumber : CardNumber.values()) {
            cardsOfOneCardType.add(new Card(cardType, cardNumber));
        }
        return cardsOfOneCardType;
    }

    public Card serve() {
        List<Card> shuffledCards = shuffleStrategy.makeShuffledCards(cards);
        return shuffledCards.get(TOP_CARD);
    }
}
