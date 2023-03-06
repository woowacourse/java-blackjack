package domain;

import strategy.ShuffleStrategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardGenerator {
    private final List<Card> cards = new ArrayList<>();

    public Queue<Card> generate(ShuffleStrategy shuffleStrategy) {
        makeCards();
        shuffleStrategy.shuffle(cards);

        return new LinkedList<>(cards);
    }

    private void makeCards() {
        for (CardPattern cardPattern : CardPattern.values()) {
            addNumbersOfPattern(cardPattern);
        }
    }

    private void addNumbersOfPattern(CardPattern cardPattern) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardNumber, cardPattern));
        }
    }
}
