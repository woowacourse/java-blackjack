package domain;

import java.util.ArrayList;
import java.util.List;

public class CardGenerator {
    public List<Card> generate() {
        List<Card> cards = new ArrayList<>();
        makeCards(cards);
        return cards;
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
