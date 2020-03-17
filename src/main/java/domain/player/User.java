package domain.player;

public class User extends Player {
	public static final int MINUS_CONVERTER = -1;
	private static final int ZERO_MONEY = 0;

	private final double bettingMoney;

	public User(String name, int bettingMoney) {
		super(name);
		this.bettingMoney = bettingMoney;
	}

	public double compareScore(Gamer gamer) {
		int toCompareScore = gamer.calculateBurstIsZeroScore();
		int userScore = calculateBurstIsZeroScore();
		if (userScore > toCompareScore) {
			return bettingMoney;
		}
		if (userScore == toCompareScore) {
			return ZERO_MONEY;
		}
		return bettingMoney * MINUS_CONVERTER;
	}
}
