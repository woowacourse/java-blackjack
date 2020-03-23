package domain.money;

/**
 *   Money class입니다.
 *
 *   @author ParkDooWon, AnHyungJu
 */
public class Money {
	private static final int MINIMUM = 10_000;
	private static final int UNIT = 10_000;
	private static final int ZERO = 0;

	private int value;

	private Money(int value) {
		this.value = value;
	}

	public static Money of(String inputMoney) {
		validate(inputMoney);
		return new Money(Integer.parseInt(inputMoney));
	}

	private static void validate(String inputMoney) {
		int money = Integer.parseInt(inputMoney);
		validateOverMinimum(money);
		validateUnit(money);
	}

	private static void validateOverMinimum(int money) {
		if (money < MINIMUM) {
			throw new IllegalArgumentException("베팅 금액은 최소 만 원입니다.");
		}
	}

	private static void validateUnit(int money) {
		if (money % UNIT != ZERO) {
			throw new IllegalArgumentException("만 원 단위로 입력해주세요.");
		}
	}

	public int getValue() {
		return value;
	}
}
