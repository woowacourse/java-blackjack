package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DealerTest {
	@Test
	void dealer_score_higher_than_player_score() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(CardLetter.NINE, CardSuit.CLOVER));
		player.processCard(new Card(CardLetter.FIVE, CardSuit.CLOVER));
		assertThat(dealer.isWin(player)).isTrue();
	}

	@Test
	void dealer_score_lower_than_player_score() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(CardLetter.NINE, CardSuit.CLOVER));
		player.processCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		assertThat(dealer.isLose(player)).isTrue();
	}

	@Test
	void dealer_score_equal_to_player_score() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(CardLetter.NINE, CardSuit.CLOVER));
		player.processCard(new Card(CardLetter.NINE, CardSuit.HEART));
		assertThat(dealer.isDraw(player)).isTrue();
	}

	@Test
	void dealer_and_player_blackjack() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		dealer.processCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
		player.processCard(new Card(CardLetter.TEN, CardSuit.HEART));
		player.processCard(new Card(CardLetter.ACE, CardSuit.HEART));
		assertThat(dealer.isDraw(player)).isTrue();
	}

	@Test
	void dealer_blackjack() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		dealer.processCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
		player.processCard(new Card(CardLetter.TEN, CardSuit.HEART));
		player.processCard(new Card(CardLetter.JACK, CardSuit.HEART));
		assertThat(dealer.isWin(player)).isTrue();
	}

	@Test
	void player_blackjack() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		dealer.processCard(new Card(CardLetter.JACK, CardSuit.CLOVER));
		player.processCard(new Card(CardLetter.TEN, CardSuit.HEART));
		player.processCard(new Card(CardLetter.ACE, CardSuit.HEART));
		assertThat(dealer.isLose(player)).isTrue();
	}

	@Test
	void dealer_bust() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		dealer.processCard(new Card(CardLetter.JACK, CardSuit.CLOVER));
		dealer.processCard(new Card(CardLetter.TWO, CardSuit.CLOVER));
		player.processCard(new Card(CardLetter.TEN, CardSuit.HEART));
		player.processCard(new Card(CardLetter.JACK, CardSuit.HEART));
		assertThat(dealer.isLose(player)).isTrue();
	}
}
