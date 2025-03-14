package blackjack.domain.card.generator;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardScore;
import blackjack.domain.card.Shape;
import java.util.Arrays;
import java.util.List;

public interface CardGenerator {

    List<Card> DECK = Arrays.stream(Shape.values())
            .flatMap(shape -> Arrays.stream(CardScore.values())
                    .map(cardScore -> new Card(shape, cardScore)))
            .toList();

    Card pickCard();
}
