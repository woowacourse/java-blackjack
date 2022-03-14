package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class CardsTest {
	@Test
	void check_lower_than_twenty_one_with_one_ace() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
		cards.addCard(new Card(CardLetter.SIX, CardSuit.CLOVER));
		cards.addCard(new Card(CardLetter.FIVE, CardSuit.CLOVER));
		assertThat(cards.getScore()).isEqualTo(12);
	}

	@Test
	void check_twenty_one_with_one_ace() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
		cards.addCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		assertThat(cards.getScore()).isEqualTo(21);
	}

	@Test
	void check_lower_than_twenty_one_with_two_ace() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
		cards.addCard(new Card(CardLetter.ACE, CardSuit.HEART));
		cards.addCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		assertThat(cards.getScore()).isEqualTo(12);
	}

	@Test
	void check_twenty_one_with_two_ace() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
		cards.addCard(new Card(CardLetter.ACE, CardSuit.HEART));
		cards.addCard(new Card(CardLetter.NINE, CardSuit.CLOVER));
		assertThat(cards.getScore()).isEqualTo(21);
	}

	@Test
	void check_lower_than_twenty_one_with_three_ace() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
		cards.addCard(new Card(CardLetter.ACE, CardSuit.HEART));
		cards.addCard(new Card(CardLetter.ACE, CardSuit.SPADE));
		cards.addCard(new Card(CardLetter.NINE, CardSuit.CLOVER));
		assertThat(cards.getScore()).isEqualTo(12);
	}

	@Test
	void check_twenty_one_with_three_ace() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
		cards.addCard(new Card(CardLetter.ACE, CardSuit.HEART));
		cards.addCard(new Card(CardLetter.ACE, CardSuit.SPADE));
		cards.addCard(new Card(CardLetter.EIGHT, CardSuit.CLOVER));
		assertThat(cards.getScore()).isEqualTo(21);
	}

	@Test
	void check_lower_than_twenty_one_with_four_ace() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
		cards.addCard(new Card(CardLetter.ACE, CardSuit.HEART));
		cards.addCard(new Card(CardLetter.ACE, CardSuit.SPADE));
		cards.addCard(new Card(CardLetter.ACE, CardSuit.DIAMOND));
		cards.addCard(new Card(CardLetter.EIGHT, CardSuit.CLOVER));
		assertThat(cards.getScore()).isEqualTo(12);
	}

	@Test
	void check_twenty_one_with_four_ace() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
		cards.addCard(new Card(CardLetter.ACE, CardSuit.HEART));
		cards.addCard(new Card(CardLetter.ACE, CardSuit.SPADE));
		cards.addCard(new Card(CardLetter.ACE, CardSuit.DIAMOND));
		cards.addCard(new Card(CardLetter.SEVEN, CardSuit.CLOVER));
		assertThat(cards.getScore()).isEqualTo(21);
	}
}
