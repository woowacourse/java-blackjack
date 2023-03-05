package domain;

import strategy.ShuffleStrategy;

import java.util.*;

public class CardGenerator {
    public Queue<Card> generate(ShuffleStrategy shuffleStrategy) {
        Queue<Card> cardDeck = new LinkedList<>();
        List<Card> cards = new ArrayList<>();
        makeCards(cards);
        shuffleStrategy.shuffle(cards);
        cardDeck.addAll(cards);
        return cardDeck;
    }

    private void makeCards(List<Card> cards) {
        for (CardPattern cardPattern : CardPattern.values()) {
            addNumbersOfPattern(cards, cardPattern);
        }
    }

    private void addNumbersOfPattern(List<Card> cards, CardPattern cardPattern) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardNumber, cardPattern));
        }
    }
}
