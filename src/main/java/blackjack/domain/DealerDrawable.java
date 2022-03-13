package blackjack.domain;

import java.util.Random;

public class DealerDrawable {

	private static final Random random = new Random();

	public static boolean chooseDraw() {
		return random.nextBoolean();
	}
}
