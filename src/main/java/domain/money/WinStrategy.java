package domain.money;

/**
 *    승리를 의미하는 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class WinStrategy implements ProfitStrategy {
	private static final double RATIO = 1.0;

	@Override
	public double calculate(Money bettingMoney) {
		return bettingMoney.getValue() * RATIO;
	}
}
