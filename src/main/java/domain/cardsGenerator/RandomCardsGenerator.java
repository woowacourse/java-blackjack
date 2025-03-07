package domain.cardsGenerator;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomCardsGenerator implements CardsGenerator {
    @Override
    public List<Card> generate() {
        List<Card> cards = new ArrayList<>();
        for (CardNumber number : CardNumber.values()) {
            for (CardShape shape : CardShape.values()) {
                cards.add(new Card(number, shape));
            }
        }
        Collections.shuffle(cards);
        return cards;
    }
}
