package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ResultCalculatorTest {
	@Test
	void calculate() {
		Players players = PlayersFactory.create("a,b,c,d");
		Dealer dealer = new Dealer();
		assertThat(ResultCalculator.calculate(dealer, players)).isInstanceOf(Results.class);
	}
}
