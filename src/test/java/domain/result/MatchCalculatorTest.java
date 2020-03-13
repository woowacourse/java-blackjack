package domain.result;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Dealer;
import domain.user.Player;

public class MatchCalculatorTest {

	@Test
	void getMatchResultsTest() {
		Player player = new Player("둔덩");
		Dealer dealer = new Dealer();

		player.addCards(Arrays.asList(new Card(Symbol.CLOVER, Type.THREE)));
		dealer.addCards(Arrays.asList(new Card(Symbol.CLOVER, Type.TWO)));

		List<MatchResult> expected = Arrays.asList(MatchResult.WIN);
		MatchCalculator matchCalculator = new MatchCalculator(Arrays.asList(player), dealer);

		assertThat(matchCalculator.getMatchResults()).isEqualTo(expected);
	}
}
