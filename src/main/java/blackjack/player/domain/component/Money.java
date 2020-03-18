package blackjack.player.domain.component;

import java.util.Objects;

// todo :  money 생성 테스트 필요
public class Money {
	private static final int BLACKJACT_MINIMUM_VALUE = 0;
	private final long money;

	private Money(long money) {
		validate(money);
		this.money = money;
	}

	private static void validate(long money) {
		Objects.requireNonNull(money, "돈의 입력이 필요합니다.");
		if (money <= BLACKJACT_MINIMUM_VALUE) {
			throw new IllegalArgumentException("돈은 자연수만 가질 수 있습니다.");
		}
	}

	public static Money createMoney(long money) {
		return new Money(money);
	}

	public static Money createMoney(String inputMoney) {
		checkEmpty(inputMoney);
		try {
			return createMoney(Long.parseLong(inputMoney));
		} catch (ArithmeticException e) {
			throw new IllegalArgumentException("숫자를 입력해야합니다.");
		}
	}

	private static void checkEmpty(String input) {
		if (input.trim().isEmpty()) {
			throw new IllegalArgumentException("돈의 입력이 필요합니다");
		}
	}
}
