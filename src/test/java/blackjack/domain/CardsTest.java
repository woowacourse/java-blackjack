package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CardsTest {
	@Test
	void check_lower_than_twenty_one_with_one_ace() {
		Cards cards = new Cards(
			List.of(new Card(CardDenomination.ACE, CardSuit.CLOVER), new Card(CardDenomination.SIX, CardSuit.CLOVER),
				new Card(CardDenomination.FIVE, CardSuit.CLOVER)));
		assertThat(cards.getScore()).isEqualTo(12);
	}

	@Test
	void check_twenty_one_with_one_ace() {
		Cards cards = new Cards(
			List.of(new Card(CardDenomination.ACE, CardSuit.CLOVER), new Card(CardDenomination.TEN, CardSuit.CLOVER)));
		assertThat(cards.getScore()).isEqualTo(21);
	}

	@Test
	void check_lower_than_twenty_one_with_two_ace() {
		Cards cards = new Cards(
			List.of(new Card(CardDenomination.ACE, CardSuit.CLOVER), new Card(CardDenomination.ACE, CardSuit.HEART),
				new Card(CardDenomination.TEN, CardSuit.CLOVER)));
		assertThat(cards.getScore()).isEqualTo(12);
	}

	@Test
	void check_twenty_one_with_two_ace() {
		Cards cards = new Cards(
			List.of(new Card(CardDenomination.ACE, CardSuit.CLOVER), new Card(CardDenomination.ACE, CardSuit.HEART),
				new Card(CardDenomination.NINE, CardSuit.CLOVER)));
		assertThat(cards.getScore()).isEqualTo(21);
	}

	@Test
	void check_lower_than_twenty_one_with_three_ace() {
		Cards cards = new Cards(
			List.of(new Card(CardDenomination.ACE, CardSuit.CLOVER), new Card(CardDenomination.ACE, CardSuit.HEART),
				new Card(CardDenomination.ACE, CardSuit.SPADE), new Card(CardDenomination.NINE, CardSuit.CLOVER)));
		assertThat(cards.getScore()).isEqualTo(12);
	}

	@Test
	void check_twenty_one_with_three_ace() {
		Cards cards = new Cards(
			List.of(new Card(CardDenomination.ACE, CardSuit.CLOVER), new Card(CardDenomination.ACE, CardSuit.HEART),
				new Card(CardDenomination.ACE, CardSuit.SPADE), new Card(CardDenomination.EIGHT, CardSuit.CLOVER)));
		assertThat(cards.getScore()).isEqualTo(21);
	}

	@Test
	void check_lower_than_twenty_one_with_four_ace() {
		Cards cards = new Cards(
			List.of(new Card(CardDenomination.ACE, CardSuit.CLOVER), new Card(CardDenomination.ACE, CardSuit.HEART),
				new Card(CardDenomination.ACE, CardSuit.SPADE), new Card(CardDenomination.ACE, CardSuit.DIAMOND),
				new Card(CardDenomination.EIGHT, CardSuit.DIAMOND)));
		assertThat(cards.getScore()).isEqualTo(12);
	}

	@Test
	void check_twenty_one_with_four_ace() {
		Cards cards = new Cards(
			List.of(new Card(CardDenomination.ACE, CardSuit.CLOVER), new Card(CardDenomination.ACE, CardSuit.HEART),
				new Card(CardDenomination.ACE, CardSuit.SPADE), new Card(CardDenomination.ACE, CardSuit.DIAMOND),
				new Card(CardDenomination.SEVEN, CardSuit.DIAMOND)));
		assertThat(cards.getScore()).isEqualTo(21);
	}
}
