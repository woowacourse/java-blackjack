package fixture;

import domain.card.Card;
import domain.card.CardsInitializer;
import java.util.List;

public class CardsInitializerFixture implements CardsInitializer {
    private final List<Card> cards;

    public CardsInitializerFixture(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public List<Card> initialize() {
        return cards;
    }
}
