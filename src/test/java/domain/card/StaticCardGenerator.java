package domain.card;

import domain.card.cardsGenerator.CardsGenerator;
import java.util.ArrayList;
import java.util.List;

public class StaticCardGenerator implements CardsGenerator {

    private final List<Card> cards;

    public StaticCardGenerator(List<Card> cards) {
        this.cards = cards;
    }

    public StaticCardGenerator() {
        this.cards = new ArrayList<>();
    }

    @Override
    public List<Card> generate() {
        return cards;
    }
}
