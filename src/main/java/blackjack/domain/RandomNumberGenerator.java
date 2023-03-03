package blackjack.domain;

import java.security.SecureRandom;

public class RandomNumberGenerator implements NumberGenerator {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    @Override
    public int generate(int size) {
        return SECURE_RANDOM.nextInt(size);
    }
}
