package blackjack.domain.role;

import java.util.Random;

public class DealerDrawChoice {

	private static final Random random = new Random();

	public static boolean chooseDraw() {
		return random.nextBoolean();
	}
}
