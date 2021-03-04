package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        List<Card> wholeCards = Arrays.stream(Denomination.values())
            .flatMap(denomination -> Arrays.stream(Shape.values())
                .map(shape -> Card.of(denomination, shape)))
            .collect(Collectors.toList());

        BlackjackController blackjackController = new BlackjackController(wholeCards);
        blackjackController.play();
    }

}
