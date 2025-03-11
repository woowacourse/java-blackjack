package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardGenerator {

    public List<Card> makeShuffled() {
        List<Card> newCards = makeCard();
        Collections.shuffle(newCards);
        return newCards;
    }

    private List<Card> makeCard() {
        List<Card> newCards = new ArrayList<>();
        for (CardShape shape : CardShape.values()) {
            Arrays.stream(CardValue.values())
                    .map(cardValue -> new Card(shape, cardValue))
                    .forEach(newCards::add);
        }
        return newCards;
    }
}
