package domain.result;

import java.util.Objects;

public class Prize {
	public static final Prize ZERO = Prize.valueOf(0);
	private static final double DEALER_PRIZE_FACTOR = -1.0;

	private final int prize;

	private Prize(int prize) {
		this.prize = prize;
	}

	public static Prize valueOf(int prize) {
		return new Prize(prize);
	}

	public Prize multiply(double prizeFactor) {
		int prizeValue = (int)(this.prize * prizeFactor);
		return Prize.valueOf(prizeValue);
	}

	public Prize calculateDealerPrize() {
		return this.multiply(DEALER_PRIZE_FACTOR);
	}

	public static Prize sum(Prize firstPrize, Prize secondPrize) {
		return Prize.valueOf(firstPrize.prize + secondPrize.prize);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Prize that = (Prize)object;
		return this.prize == that.prize;
	}

	@Override
	public int hashCode() {
		return Objects.hash(prize);
	}

	@Override
	public String toString() {
		return String.valueOf(prize);
	}
}
