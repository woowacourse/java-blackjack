package domain.money;

/**
 *    패배를 의미하는 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class DefeatStrategy implements ProfitStrategy {
	private static final int RATIO = -1;

	@Override
	public double calculate(Money bettingMoney) {
		return bettingMoney.getValue() * RATIO;
	}
}
