package blackjack.domain.card.generator;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGenerator implements CardGenerateStrategy {

    @Override
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
