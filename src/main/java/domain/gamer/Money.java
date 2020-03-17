package domain.gamer;

public class Money {
	private static final int MIN = 0;
	public static final Money ZERO = new Money(MIN);

	private final double money;

	private Money(double money) {
		validate(money);
		this.money = money;
	}

	private void validate(double money) {
		if (money < MIN) {
			throw new IllegalArgumentException("Money는 음수가 될 수 없습니다.");
		}
	}

	public static Money of(double money) {
		return new Money(money);
	}

	public static Money of(String money) {
		return of(stringToDouble(money));
	}

	private static double stringToDouble(String money) {
		try {
			return Double.parseDouble(money);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(String.format("%s 는 잘못된 형태의 입니다.", money));
		}
	}

	public double multiply(double ratio) {
		return this.money * ratio;
	}
}
