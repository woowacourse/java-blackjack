package blackjack.mock;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGenerator;
import java.util.List;

public class CardGeneratorMock extends CardGenerator {

    @Override
    public List<Card> makeShuffled() {
        return super.makeShuffled();
    }
}
