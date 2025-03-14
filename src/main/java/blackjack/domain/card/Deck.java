package blackjack.domain.card;

import blackjack.domain.card.generator.CardGenerator;
import java.util.stream.IntStream;

public class Deck {

    private final CardGenerator cardGenerator;

    public Deck(final CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
    }

    public Hand spreadCards(final int count) {
        return new Hand(IntStream.range(0, count)
                .mapToObj(o -> cardGenerator.pickCard())
                .toList());
    }
}
