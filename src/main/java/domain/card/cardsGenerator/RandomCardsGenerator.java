package domain.card.cardsGenerator;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomCardsGenerator implements CardsGenerator {

    private static final List<Card> CARDS = createCards();

    private static List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        for (CardNumber number : CardNumber.values()) {
            for (CardShape shape : CardShape.values()) {
                cards.add(new Card(number, shape));
            }
        }
        return cards;
    }

    @Override
    public List<Card> generate() {
        Collections.shuffle(CARDS);
        return new ArrayList<>(CARDS);
    }
}
