package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DealerTest {
	@Test
	void dealer_normal_player_normal_higher_score() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.addCard(new Card(CardDenomination.NINE, CardSuit.CLOVER));
		player.addCard(new Card(CardDenomination.FIVE, CardSuit.CLOVER));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(win && !draw && !lose).isTrue();
	}

	@Test
	void dealer_normal_player_normal_lower_score() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.addCard(new Card(CardDenomination.NINE, CardSuit.CLOVER));
		player.addCard(new Card(CardDenomination.TEN, CardSuit.CLOVER));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(!win & !draw & lose).isTrue();
	}

	@Test
	void dealer_normal_player_normal_equal_score() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.addCard(new Card(CardDenomination.NINE, CardSuit.CLOVER));
		player.addCard(new Card(CardDenomination.NINE, CardSuit.HEART));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(!win && draw && !lose).isTrue();
	}

	@Test
	void dealer_blackjack_player_blackjack() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.addCard(new Card(CardDenomination.TEN, CardSuit.CLOVER));
		dealer.addCard(new Card(CardDenomination.ACE, CardSuit.CLOVER));
		player.addCard(new Card(CardDenomination.TEN, CardSuit.HEART));
		player.addCard(new Card(CardDenomination.ACE, CardSuit.HEART));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(!win && draw && !lose).isTrue();
	}

	@Test
	void dealer_blackjack_player_normal() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.addCard(new Card(CardDenomination.TEN, CardSuit.CLOVER));
		dealer.addCard(new Card(CardDenomination.ACE, CardSuit.CLOVER));
		player.addCard(new Card(CardDenomination.TEN, CardSuit.HEART));
		player.addCard(new Card(CardDenomination.TEN, CardSuit.HEART));
		player.addCard(new Card(CardDenomination.ACE, CardSuit.HEART));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(win && !draw && !lose).isTrue();
	}

	@Test
	void dealer_normal_player_blackjack() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.addCard(new Card(CardDenomination.TEN, CardSuit.CLOVER));
		dealer.addCard(new Card(CardDenomination.JACK, CardSuit.CLOVER));
		dealer.addCard(new Card(CardDenomination.ACE, CardSuit.CLOVER));
		player.addCard(new Card(CardDenomination.TEN, CardSuit.HEART));
		player.addCard(new Card(CardDenomination.ACE, CardSuit.HEART));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(!win && !draw && lose).isTrue();
	}

	@Test
	void dealer_bust_player_normal() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.addCard(new Card(CardDenomination.TEN, CardSuit.CLOVER));
		dealer.addCard(new Card(CardDenomination.JACK, CardSuit.CLOVER));
		dealer.addCard(new Card(CardDenomination.TWO, CardSuit.CLOVER));
		player.addCard(new Card(CardDenomination.TEN, CardSuit.HEART));
		player.addCard(new Card(CardDenomination.JACK, CardSuit.HEART));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(!win && !draw && lose).isTrue();
	}

	@Test
	void dealer_bust_player_blackjack() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.addCard(new Card(CardDenomination.TEN, CardSuit.CLOVER));
		dealer.addCard(new Card(CardDenomination.JACK, CardSuit.CLOVER));
		dealer.addCard(new Card(CardDenomination.TWO, CardSuit.CLOVER));
		player.addCard(new Card(CardDenomination.TEN, CardSuit.HEART));
		player.addCard(new Card(CardDenomination.ACE, CardSuit.HEART));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(!win && !draw && lose).isTrue();
	}

	@Test
	void dealer_bust_player_bust() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.addCard(new Card(CardDenomination.TEN, CardSuit.CLOVER));
		dealer.addCard(new Card(CardDenomination.JACK, CardSuit.CLOVER));
		dealer.addCard(new Card(CardDenomination.TWO, CardSuit.CLOVER));
		player.addCard(new Card(CardDenomination.TEN, CardSuit.HEART));
		player.addCard(new Card(CardDenomination.JACK, CardSuit.HEART));
		player.addCard(new Card(CardDenomination.TWO, CardSuit.HEART));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(win && !draw && !lose).isTrue();
	}

	@Test
	void dealer_normal_player_bust() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.addCard(new Card(CardDenomination.TEN, CardSuit.CLOVER));
		dealer.addCard(new Card(CardDenomination.JACK, CardSuit.CLOVER));
		player.addCard(new Card(CardDenomination.TEN, CardSuit.HEART));
		player.addCard(new Card(CardDenomination.JACK, CardSuit.HEART));
		player.addCard(new Card(CardDenomination.TWO, CardSuit.HEART));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(win && !draw && !lose).isTrue();
	}

	@Test
	void dealer_blackjack_player_bust() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.addCard(new Card(CardDenomination.TEN, CardSuit.CLOVER));
		dealer.addCard(new Card(CardDenomination.ACE, CardSuit.CLOVER));
		player.addCard(new Card(CardDenomination.TEN, CardSuit.HEART));
		player.addCard(new Card(CardDenomination.JACK, CardSuit.HEART));
		player.addCard(new Card(CardDenomination.TWO, CardSuit.HEART));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(win && !draw && !lose).isTrue();
	}
}
