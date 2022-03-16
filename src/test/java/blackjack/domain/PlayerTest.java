package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PlayerTest {
	@Test
	void dealer_normal_player_normal_higher_score() {
		Dealer dealer = new Dealer(new Cards(List.of(new Card(CardDenomination.NINE, CardSuit.CLOVER))));
		Player player = new Player(new Cards(List.of(new Card(CardDenomination.FIVE, CardSuit.CLOVER))),
			new Name("pobi"));
		assertAll(() -> assertThat(player.isWin(dealer)).isFalse(),
			() -> assertThat(player.isDraw(dealer)).isFalse(),
			() -> assertThat(player.isLose(dealer)).isTrue());
	}

	@Test
	void dealer_normal_player_normal_lower_score() {
		Dealer dealer = new Dealer(new Cards(List.of(new Card(CardDenomination.NINE, CardSuit.CLOVER))));
		Player player = new Player(new Cards(List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER))),
			new Name("pobi"));
		assertAll(() -> assertThat(player.isWin(dealer)).isTrue(),
			() -> assertThat(player.isDraw(dealer)).isFalse(),
			() -> assertThat(player.isLose(dealer)).isFalse());
	}

	@Test
	void dealer_normal_player_normal_equal_score() {
		Dealer dealer = new Dealer(new Cards(List.of(new Card(CardDenomination.NINE, CardSuit.CLOVER))));
		Player player = new Player(new Cards(List.of(new Card(CardDenomination.NINE, CardSuit.CLOVER))),
			new Name("pobi"));
		assertAll(() -> assertThat(player.isWin(dealer)).isFalse(),
			() -> assertThat(player.isDraw(dealer)).isTrue(),
			() -> assertThat(player.isLose(dealer)).isFalse());
	}

	@Test
	void dealer_blackjack_player_blackjack() {
		Dealer dealer = new Dealer(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER), new Card(CardDenomination.ACE, CardSuit.CLOVER))));
		Player player = new Player(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.ACE, CardSuit.HEART))),
			new Name("pobi"));
		assertAll(() -> assertThat(player.isWin(dealer)).isFalse(),
			() -> assertThat(player.isDraw(dealer)).isTrue(),
			() -> assertThat(player.isLose(dealer)).isFalse());
	}

	@Test
	void dealer_blackjack_player_normal() {
		Dealer dealer = new Dealer(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER), new Card(CardDenomination.ACE, CardSuit.CLOVER))));
		Player player = new Player(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.TEN, CardSuit.SPADE),
				new Card(CardDenomination.ACE, CardSuit.HEART))), new Name("pobi"));
		assertAll(() -> assertThat(player.isWin(dealer)).isFalse(),
			() -> assertThat(player.isDraw(dealer)).isFalse(),
			() -> assertThat(player.isLose(dealer)).isTrue());
	}

	@Test
	void dealer_normal_player_blackjack() {
		Dealer dealer = new Dealer(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER), new Card(CardDenomination.JACK, CardSuit.CLOVER),
				new Card(CardDenomination.ACE, CardSuit.CLOVER))));
		Player player = new Player(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.ACE, CardSuit.HEART))),
			new Name("pobi"));
		assertAll(() -> assertThat(player.isWin(dealer)).isTrue(),
			() -> assertThat(player.isDraw(dealer)).isFalse(),
			() -> assertThat(player.isLose(dealer)).isFalse());
	}

	@Test
	void dealer_bust_player_normal() {
		Dealer dealer = new Dealer(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER), new Card(CardDenomination.JACK, CardSuit.CLOVER),
				new Card(CardDenomination.TWO, CardSuit.SPADE))));
		Player player = new Player(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.JACK, CardSuit.HEART))),
			new Name("pobi"));
		assertAll(() -> assertThat(player.isWin(dealer)).isTrue(),
			() -> assertThat(player.isDraw(dealer)).isFalse(),
			() -> assertThat(player.isLose(dealer)).isFalse());
	}

	@Test
	void dealer_bust_player_blackjack() {
		Dealer dealer = new Dealer(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER), new Card(CardDenomination.JACK, CardSuit.CLOVER),
				new Card(CardDenomination.TWO, CardSuit.CLOVER))));
		Player player = new Player(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.ACE, CardSuit.HEART))),
			new Name("pobi"));
		assertAll(() -> assertThat(player.isWin(dealer)).isTrue(),
			() -> assertThat(player.isDraw(dealer)).isFalse(),
			() -> assertThat(player.isLose(dealer)).isFalse());
	}

	@Test
	void dealer_bust_player_bust() {
		Dealer dealer = new Dealer(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER), new Card(CardDenomination.JACK, CardSuit.CLOVER),
				new Card(CardDenomination.TWO, CardSuit.CLOVER))));
		Player player = new Player(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.JACK, CardSuit.HEART),
				new Card(CardDenomination.TWO, CardSuit.HEART))), new Name("pobi"));
		assertAll(() -> assertThat(player.isWin(dealer)).isFalse(),
			() -> assertThat(player.isDraw(dealer)).isFalse(),
			() -> assertThat(player.isLose(dealer)).isTrue());
	}

	@Test
	void dealer_normal_player_bust() {
		Dealer dealer = new Dealer(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER), new Card(CardDenomination.JACK, CardSuit.CLOVER))));
		Player player = new Player(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.JACK, CardSuit.HEART),
				new Card(CardDenomination.TWO, CardSuit.HEART))), new Name("pobi"));
		assertAll(() -> assertThat(player.isWin(dealer)).isFalse(),
			() -> assertThat(player.isDraw(dealer)).isFalse(),
			() -> assertThat(player.isLose(dealer)).isTrue());
	}

	@Test
	void dealer_blackjack_player_bust() {
		Dealer dealer = new Dealer(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER), new Card(CardDenomination.ACE, CardSuit.CLOVER))));
		Player player = new Player(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.JACK, CardSuit.HEART),
				new Card(CardDenomination.TWO, CardSuit.HEART))), new Name("pobi"));
		assertAll(() -> assertThat(player.isWin(dealer)).isFalse(),
			() -> assertThat(player.isDraw(dealer)).isFalse(),
			() -> assertThat(player.isLose(dealer)).isTrue());
	}
}
