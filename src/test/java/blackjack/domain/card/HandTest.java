package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import blackjack.domain.user.hand.Hand;
import blackjack.domain.user.hand.Score;

class HandTest {
	@Test
	void Hand_GenerateInstance() {
		assertThat(new Hand()).isInstanceOf(Hand.class);
	}

	@Test
	void add_Card_AddCardToHand() {
		Hand hand = new Hand();
		Card card = new Card(Symbol.ACE, Type.CLUB);
		hand.add(card);

		assertThat(hand).extracting("cards").asList().contains(card);
	}

	@Test
	void add_CardList_AddCardsToHand() {
		Hand hand = new Hand();
		List<Card> cards = Arrays.asList(
			new Card(Symbol.ACE, Type.CLUB),
			new Card(Symbol.EIGHT, Type.DIAMOND));
		hand.add(cards);

		assertThat(hand).extracting("cards").asList().containsAll(cards);
	}

	@Test
	void calculateScore_SumCards() {
		Hand hand = new Hand();
		List<Card> cards = Arrays.asList(
			new Card(Symbol.TWO, Type.CLUB),
			new Card(Symbol.EIGHT, Type.DIAMOND));
		hand.add(cards);

		Score expected = Score.valueOf(10);
		assertThat(hand.calculateScore()).isEqualTo(expected);
	}

	@Test
	void calculateScore_WithAceCard_SumCards() {
		Hand hand = new Hand();
		List<Card> cards = Arrays.asList(
			new Card(Symbol.ACE, Type.CLUB),
			new Card(Symbol.EIGHT, Type.DIAMOND));
		hand.add(cards);

		Score expected = Score.valueOf(19);
		assertThat(hand.calculateScore()).isEqualTo(expected);
	}

	@Test
	void calculateBustHandledScore_BustScore_ReturnZeroScore() {
		Hand hand = new Hand();
		List<Card> cards = Arrays.asList(
			new Card(Symbol.JACK, Type.DIAMOND),
			new Card(Symbol.QUEEN, Type.HEART),
			new Card(Symbol.TWO, Type.SPADE));
		hand.add(cards);

		Score expected = Score.ZERO;
		assertThat(hand.calculateBustHandledScore()).isEqualTo(expected);
	}
}
