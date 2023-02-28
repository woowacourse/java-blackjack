package domain;

import java.util.*;

public class CardGenerator {
    public Queue<Card> generate() {
        List<Card> cards = new ArrayList<>();
        makeCards(cards);
        return suffleCards(cards);
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

    private Queue<Card> suffleCards(List<Card> cards){
        Queue<Card> suffledCards = new LinkedList<>();
        Collections.shuffle(cards);
        suffledCards.addAll(cards);
        return suffledCards;
    }

}
