package blackjack.domain;

import java.util.Random;

public class RandomCardGenerator implements CardGenerator {
    private static final Random RANDOM = new Random();

    @Override
    public Card generate() {
        return new Card();
    }
}
