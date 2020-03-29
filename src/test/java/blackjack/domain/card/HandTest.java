package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import blackjack.domain.result.Score;

class HandTest {
	@Test
	void Hand_GenerateInstance() {
		assertThat(new Hand()).isInstanceOf(Hand.class);
	}

	@Test
	void add_Card_AddCardToHand() {
		Hand hand = new Hand();
		Card card = Card.of(Symbol.ACE, Type.CLUB);
		hand.add(card);

		assertThat(hand).extracting("cards").asList().contains(card);
	}

	@Test
	void add_CardList_AddCardsToHand() {
		Hand hand = new Hand();
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.ACE, Type.CLUB),
			Card.of(Symbol.EIGHT, Type.DIAMOND));
		hand.add(cards);

		assertThat(hand).extracting("cards").asList().containsAll(cards);
	}

	@Test
	void calculateScore_CardsInHand_CalculatedScore() {
		Hand hand = new Hand();
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.ACE, Type.CLUB),
			Card.of(Symbol.EIGHT, Type.DIAMOND));
		hand.add(cards);

		Score expected = Score.valueOf(19);
		assertThat(hand.calculateScore()).isEqualTo(expected);
	}
}
