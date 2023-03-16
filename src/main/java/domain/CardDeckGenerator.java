package domain;

import java.util.LinkedList;
import java.util.Queue;

public class CardDeckGenerator {
    public Queue<Card> generate() {
        Queue<Card> cardDeck = new LinkedList<>();
        makeCards(cardDeck);
        return cardDeck;
    }

    private void makeCards(Queue<Card> cardDeck) {
        for (CardPattern cardPattern : CardPattern.values()) {
            addNumbersOfPattern(cardDeck, cardPattern);
        }
    }

    private void addNumbersOfPattern(Queue<Card> cardDeck, CardPattern cardPattern) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cardDeck.add(new Card(cardNumber, cardPattern));
        }
    }

}
