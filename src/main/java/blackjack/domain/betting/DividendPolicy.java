package blackjack.domain.betting;

public interface DividendPolicy {

    int calculate(int betMoney, BettingResult bettingResult);
}
