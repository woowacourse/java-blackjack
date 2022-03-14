package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class CardsTest {
	@Test
	void check_lower_than_twenty_one_with_one_ace() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.CLOVER));
		cards.addCard(new Card(CardDenomination.SIX, CardSuit.CLOVER));
		cards.addCard(new Card(CardDenomination.FIVE, CardSuit.CLOVER));
		assertThat(cards.getScore()).isEqualTo(12);
	}

	@Test
	void check_twenty_one_with_one_ace() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.CLOVER));
		cards.addCard(new Card(CardDenomination.TEN, CardSuit.CLOVER));
		assertThat(cards.getScore()).isEqualTo(21);
	}

	@Test
	void check_lower_than_twenty_one_with_two_ace() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.CLOVER));
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.HEART));
		cards.addCard(new Card(CardDenomination.TEN, CardSuit.CLOVER));
		assertThat(cards.getScore()).isEqualTo(12);
	}

	@Test
	void check_twenty_one_with_two_ace() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.CLOVER));
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.HEART));
		cards.addCard(new Card(CardDenomination.NINE, CardSuit.CLOVER));
		assertThat(cards.getScore()).isEqualTo(21);
	}

	@Test
	void check_lower_than_twenty_one_with_three_ace() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.CLOVER));
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.HEART));
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.SPADE));
		cards.addCard(new Card(CardDenomination.NINE, CardSuit.CLOVER));
		assertThat(cards.getScore()).isEqualTo(12);
	}

	@Test
	void check_twenty_one_with_three_ace() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.CLOVER));
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.HEART));
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.SPADE));
		cards.addCard(new Card(CardDenomination.EIGHT, CardSuit.CLOVER));
		assertThat(cards.getScore()).isEqualTo(21);
	}

	@Test
	void check_lower_than_twenty_one_with_four_ace() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.CLOVER));
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.HEART));
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.SPADE));
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.DIAMOND));
		cards.addCard(new Card(CardDenomination.EIGHT, CardSuit.CLOVER));
		assertThat(cards.getScore()).isEqualTo(12);
	}

	@Test
	void check_twenty_one_with_four_ace() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.CLOVER));
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.HEART));
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.SPADE));
		cards.addCard(new Card(CardDenomination.ACE, CardSuit.DIAMOND));
		cards.addCard(new Card(CardDenomination.SEVEN, CardSuit.CLOVER));
		assertThat(cards.getScore()).isEqualTo(21);
	}
}
