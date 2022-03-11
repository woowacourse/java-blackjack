package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DealerTest {
	@Test
	void dealer_score_higher_than_player_score() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(Number.NINE, Type.CLOVER));
		player.processCard(new Card(Number.FIVE, Type.CLOVER));
		assertThat(dealer.isHigher(player)).isTrue();
	}

	@Test
	void dealer_score_lower_than_player_score() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(Number.NINE, Type.CLOVER));
		player.processCard(new Card(Number.TEN, Type.CLOVER));
		assertThat(dealer.isHigher(player)).isFalse();
	}

	@Test
	void dealer_score_equal_to_player_score() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(Number.NINE, Type.CLOVER));
		player.processCard(new Card(Number.NINE, Type.HEART));
		assertThat(dealer.isEqaul(player)).isTrue();
	}
}
