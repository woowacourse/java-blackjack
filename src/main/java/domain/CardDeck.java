package domain;

import java.util.*;

public class CardDeck {

    private final Deque<Card> cardDeck;

    private CardDeck(Deque<Card> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public static CardDeck generateCardDeck() {
        List<Card> cards = new ArrayList<>();
        makeCards(cards);
        shuffleCards(cards);
        Deque<Card> cardDeck = new ArrayDeque<>(cards);
        return new CardDeck(cardDeck);
    }

    private static void makeCards(List<Card> cards) {
        for (CardType cardType : CardType.values()) {
            addCards(cards, cardType);
        }
    }

    private static void addCards(List<Card> cards, CardType cardType) {
        for (CardValue cardValue : CardValue.values()) {
            cards.add(new Card(cardType, cardValue));
        }
    }

    private static void shuffleCards(List<Card> cards) {
        Collections.shuffle(cards);
    }

    public Card pickCard() {
        return cardDeck.pop();
    }

    public Deque<Card> getCardDeck() {
        return cardDeck;
    }

//    private final Stack<Card> initPool = new Stack<>();
//
//    {
//        init();
//    }
//
//    private void init() {
//        for (Suit suit : Suit.values()) {
//            initNumber(suit);
//        }
//    }
//
//    private void initNumber(Suit suit) {
//        for (Number number : Number.values()) {
//            initPool.add(new Card(suit, number));
//        }
//    }
//
//    public void shuffle() {
//        Collections.shuffle(initPool);
//    }
//
//    public Card pick() {
//        return initPool.pop();
//    }
}
