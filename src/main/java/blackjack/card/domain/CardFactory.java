package blackjack.card.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardFactory implements CardCreateStrategy {

    @Override
    public List<Card> getCards() {
        return new ArrayList<>(Arrays.asList(Card.values()));
    }
}
