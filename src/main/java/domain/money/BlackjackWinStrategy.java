package domain.money;

/**
 *    블랙잭으로 승리를 의미하는 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class BlackjackWinStrategy implements ProfitStrategy {
	private static final double RATIO = 1.5;

	@Override
	public double calculate(Money bettingMoney) {
		return bettingMoney.getValue() * RATIO;
	}
}