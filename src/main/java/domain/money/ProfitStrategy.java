package domain.money;

/**
 *    이익을 계산하기 위한 전략 인터페이스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public interface ProfitStrategy {
	double calculate(Money bettingMoney);
}