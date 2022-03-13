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
		assertThat(dealer.isWin(player)).isTrue();
	}

	@Test
	void dealer_score_lower_than_player_score() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(Number.NINE, Type.CLOVER));
		player.processCard(new Card(Number.TEN, Type.CLOVER));
		assertThat(dealer.isLose(player)).isTrue();
	}

	@Test
	void dealer_score_equal_to_player_score() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(Number.NINE, Type.CLOVER));
		player.processCard(new Card(Number.NINE, Type.HEART));
		assertThat(dealer.isDraw(player)).isTrue();
	}

	@Test
	void dealer_and_player_blackjack() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(Number.TEN, Type.CLOVER));
		dealer.processCard(new Card(Number.ACE, Type.CLOVER));
		player.processCard(new Card(Number.TEN, Type.HEART));
		player.processCard(new Card(Number.ACE, Type.HEART));
		assertThat(dealer.isDraw(player)).isTrue();
	}

	@Test
	void dealer_blackjack() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(Number.TEN, Type.CLOVER));
		dealer.processCard(new Card(Number.ACE, Type.CLOVER));
		player.processCard(new Card(Number.TEN, Type.HEART));
		player.processCard(new Card(Number.JACK, Type.HEART));
		assertThat(dealer.isWin(player)).isTrue();
	}

	@Test
	void player_blackjack() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(Number.TEN, Type.CLOVER));
		dealer.processCard(new Card(Number.JACK, Type.CLOVER));
		player.processCard(new Card(Number.TEN, Type.HEART));
		player.processCard(new Card(Number.ACE, Type.HEART));
		assertThat(dealer.isLose(player)).isTrue();
	}

	@Test
	void dealer_bust() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(Number.TEN, Type.CLOVER));
		dealer.processCard(new Card(Number.JACK, Type.CLOVER));
		dealer.processCard(new Card(Number.TWO, Type.CLOVER));
		player.processCard(new Card(Number.TEN, Type.HEART));
		player.processCard(new Card(Number.JACK, Type.HEART));
		assertThat(dealer.isLose(player)).isTrue();
	}
}
