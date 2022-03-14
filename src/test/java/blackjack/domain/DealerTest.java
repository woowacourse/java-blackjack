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
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(win && !draw && !lose).isTrue();
	}

	@Test
	void dealer_score_lower_than_player_score() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(CardLetter.NINE, CardSuit.CLOVER));
		player.processCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(!win & !draw & lose).isTrue();
	}

	@Test
	void dealer_score_equal_to_player_score() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(CardLetter.NINE, CardSuit.CLOVER));
		player.processCard(new Card(CardLetter.NINE, CardSuit.HEART));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(!win && draw && !lose).isTrue();
	}

	@Test
	void dealer_and_player_blackjack() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		dealer.processCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
		player.processCard(new Card(CardLetter.TEN, CardSuit.HEART));
		player.processCard(new Card(CardLetter.ACE, CardSuit.HEART));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(!win && draw && !lose).isTrue();
	}

	@Test
	void dealer_blackjack() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		dealer.processCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
		player.processCard(new Card(CardLetter.TEN, CardSuit.HEART));
		player.processCard(new Card(CardLetter.TEN, CardSuit.HEART));
		player.processCard(new Card(CardLetter.ACE, CardSuit.HEART));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(win && !draw && !lose).isTrue();
	}

	@Test
	void player_blackjack() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		dealer.processCard(new Card(CardLetter.JACK, CardSuit.CLOVER));
		dealer.processCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
		player.processCard(new Card(CardLetter.TEN, CardSuit.HEART));
		player.processCard(new Card(CardLetter.ACE, CardSuit.HEART));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(!win && !draw && lose).isTrue();
	}

	@Test
	void dealer_bust_player_not_dust() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		dealer.processCard(new Card(CardLetter.JACK, CardSuit.CLOVER));
		dealer.processCard(new Card(CardLetter.TWO, CardSuit.CLOVER));
		player.processCard(new Card(CardLetter.TEN, CardSuit.HEART));
		player.processCard(new Card(CardLetter.JACK, CardSuit.HEART));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(!win && !draw && lose).isTrue();
	}

	@Test
	void dealer_bust_player_bust() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		dealer.processCard(new Card(CardLetter.JACK, CardSuit.CLOVER));
		dealer.processCard(new Card(CardLetter.TWO, CardSuit.CLOVER));
		player.processCard(new Card(CardLetter.TEN, CardSuit.HEART));
		player.processCard(new Card(CardLetter.JACK, CardSuit.HEART));
		player.processCard(new Card(CardLetter.TWO, CardSuit.HEART));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(win && !draw && !lose).isTrue();
	}

	@Test
	void dealer_not_bust_player_bust() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		dealer.processCard(new Card(CardLetter.JACK, CardSuit.CLOVER));
		player.processCard(new Card(CardLetter.TEN, CardSuit.HEART));
		player.processCard(new Card(CardLetter.JACK, CardSuit.HEART));
		player.processCard(new Card(CardLetter.TWO, CardSuit.HEART));
		boolean win = dealer.isWin(player);
		boolean draw = dealer.isDraw(player);
		boolean lose = dealer.isLose(player);
		assertThat(win && !draw && !lose).isTrue();
	}
}
