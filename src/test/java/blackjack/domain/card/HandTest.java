package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import blackjack.domain.exceptions.InvalidHandException;
import blackjack.domain.result.ResultScore;
import blackjack.domain.result.Score;
import blackjack.domain.result.ScoreType;

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

	@ParameterizedTest
	@NullSource
	void validate_NullIntoAdd_InvalidHandExceptionThrown(Card card) {
		Hand hand = new Hand();

		assertThatThrownBy(() -> hand.add(card))
			.isInstanceOf(InvalidHandException.class)
			.hasMessage(InvalidHandException.NULL);
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

	@ParameterizedTest
	@NullSource
	void validate_NullCardsIntoAdd_InvalidHandExceptionThrown(List<Card> cards) {
		Hand hand = new Hand();

		assertThatThrownBy(() -> hand.add(cards))
			.isInstanceOf(InvalidHandException.class)
			.hasMessage(InvalidHandException.EMPTY);
	}

	@Test
	void calculateResultScore_CardsInHand_CalculatedScore() {
		Hand hand = new Hand();
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.ACE, Type.CLUB),
			Card.of(Symbol.QUEEN, Type.DIAMOND));
		hand.add(cards);

		ResultScore expected = new ResultScore(Score.valueOf(21), ScoreType.BLACKJACK);
		assertThat(hand.calculateResultScore()).isEqualTo(expected);
	}
}
