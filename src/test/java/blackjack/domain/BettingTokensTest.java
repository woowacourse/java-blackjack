package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.betting.BettingToken;
import blackjack.domain.betting.BettingTokens;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BettingTokensTest {
	@Test
	void add_one_betting_money_to_full_size() {
		BettingTokens bettingTokens = new BettingTokens(List.of(new BettingToken(300), new BettingToken(200)), 2);
		assertThatThrownBy(() -> bettingTokens.addBettingMoney(new BettingToken(100))).isInstanceOf(IllegalArgumentException.class);
	}
}
