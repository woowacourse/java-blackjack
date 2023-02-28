package blackjack.domain;

import java.security.SecureRandom;

public class RandomNumberGenerator implements NumberGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();

    @Override
    public int generate(int size) {
        return secureRandom.nextInt(size);
    }
}
