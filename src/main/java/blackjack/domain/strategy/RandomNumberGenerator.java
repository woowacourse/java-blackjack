package blackjack.domain.strategy;

import java.util.Random;

import blackjack.domain.Card;

public class RandomNumberGenerator implements NumberGenerator {
	private static NumberGenerator numberGenerator = new RandomNumberGenerator();

	public static NumberGenerator getInstance() {
		return numberGenerator;
	}

	@Override
	public int generateNumber() {
		Random random = new Random();
		return random.nextInt(Card.SIZE);
	}
}
