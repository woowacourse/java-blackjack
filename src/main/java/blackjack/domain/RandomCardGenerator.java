package blackjack.domain;

import java.util.Random;

import static blackjack.domain.CardNumber.ACE;
import static blackjack.domain.CardShape.SPADE;

public class RandomCardGenerator implements CardGenerator {
    private static final Random RANDOM = new Random();

    @Override
    public Card generate() {
        return new Card(ACE, SPADE);
    }
}
