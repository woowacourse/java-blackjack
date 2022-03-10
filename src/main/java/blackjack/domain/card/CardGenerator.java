package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGenerator {

    public List<Card> generate() {
        List<Card> cards = generateCards();
        Collections.shuffle(cards);
        return cards;
    }

    private List<Card> generateCards() {
        List<Card> cards = new ArrayList<>();
        for (CardType type : CardType.values()) {
            createCardByType(cards, type);
        }
        return cards;
    }

    private void createCardByType(List<Card> cards, CardType type) {
        for (CardNumber number : CardNumber.values()) {
            cards.add(new Card(number, type));
        }
    }
}
