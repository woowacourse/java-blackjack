package domain.money;

/**
 *   Money class입니다.
 *
 *   @author ParkDooWon, AnHyungJu
 */
public class Money {
	private static final int MINIMUM = 10000;
	private static final int UNIT = 10000;
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
		int money = validateNumber(inputMoney);
		validateOverMinimum(money);
		validateUnit(money);
	}

	private static int validateNumber(String inputMoney) {
		try {
			return Integer.parseInt(inputMoney);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(String.format("베팅 금액은 양수로 입력해주세요. 입력값 : %s", inputMoney));
		}
	}

	private static void validateOverMinimum(int money) {
		if (money < MINIMUM) {
			throw new IllegalArgumentException(String.format("최소 만원 이상으로 입력해주세요. 입력값 : %d", money));
		}
	}

	private static void validateUnit(int money) {
		if (money % UNIT != ZERO) {
			throw new IllegalArgumentException(String.format("만 원 단위로 입력해주세요. 입력값 : %d", money));
		}
	}

	public int getValue() {
		return value;
	}
}