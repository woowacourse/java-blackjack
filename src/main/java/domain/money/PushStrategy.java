package domain.money;

/**
 *    무승부를 의미하는 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class PushStrategy implements ProfitStrategy {
	private static final int RATIO = 0;

	@Override
	public double calculate(Money bettingMoney) {
		return bettingMoney.getValue() * RATIO;
	}
}
