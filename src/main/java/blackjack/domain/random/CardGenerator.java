package blackjack.domain.random;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import java.util.Arrays;
import java.util.List;

public interface CardGenerator {

    List<Card> DECKS = Arrays.stream(Shape.values())
            .flatMap(shape -> Arrays.stream(Denomination.values())
                    .map(denomination -> new Card(shape, denomination)))
            .toList();

    Card pickCard();
}
