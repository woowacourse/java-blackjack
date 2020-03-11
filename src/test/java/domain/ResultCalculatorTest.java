package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ResultCalculatorTest {
	@Test
	void getResults() {
		Players players = new Players(PlayerFactory.create("a,b,c,d"));
		Dealer dealer = new Dealer();
		assertThat(ResultCalculator.getResults(dealer, players)).isInstanceOf(Results.class);
	}
}
