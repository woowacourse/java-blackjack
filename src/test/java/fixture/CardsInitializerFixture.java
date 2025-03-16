package fixture;

import domain.card.Card;
import domain.card.CardsInitializer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardsInitializerFixture implements CardsInitializer {
    private final List<Card> cards;

    public CardsInitializerFixture(Card... cards) {
        this.cards = new ArrayList<>(Arrays.stream(cards).toList());
    }

    @Override
    public List<Card> initialize() {
        return cards;
    }
}
