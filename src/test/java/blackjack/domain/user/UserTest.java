package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.exceptions.InvalidDeckException;
import blackjack.domain.exceptions.InvalidUserException;
import blackjack.domain.result.ResultScore;
import blackjack.domain.result.Score;
import blackjack.domain.result.ScoreType;

class UserTest {
	@Test
	void User_InputUserName_GenerateInstance() {
		User user = new Dealer("user");

		assertThat(user).isInstanceOf(User.class);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void validate_InvalidUserName_InvalidUserExceptionThrown(String name) {
		assertThatThrownBy(() -> new Dealer(name))
			.isInstanceOf(InvalidUserException.class)
			.hasMessage(InvalidUserException.NULL_OR_EMPTY);
	}

	@ParameterizedTest
	@NullSource
	void validate_NullHand_InvalidUserExceptionThrown(Hand hand) {
		assertThatThrownBy(() -> new Dealer("dealer", hand))
			.isInstanceOf(InvalidUserException.class)
			.hasMessage(InvalidUserException.NULL_HAND);
	}

	@Test
	void hit_DrawCard_AddDrawCardToHand() {
		User user = new Dealer("user");
		Deck deck = new Deck(CardFactory.create());
		user.hit(deck);

		assertThat(user).extracting("hand").asList()
			.hasOnlyElementsOfType(Card.class)
			.hasSize(1);
	}

	@Test
	void validate_Null_InvalidDeckExceptionThrown() {
		User user = new Dealer("user");

		assertThatThrownBy(() -> user.hit(null))
			.isInstanceOf(InvalidDeckException.class)
			.hasMessage(InvalidDeckException.NULL);
	}

	@Test
	void hit_DrawNumberOfCards_AddDrawCardsToHand() {
		User user = new Dealer("user");
		Deck deck = new Deck(CardFactory.create());
		user.hit(deck, 2);

		assertThat(user).extracting("hand").asList()
			.hasOnlyElementsOfType(Card.class)
			.hasSize(2);
	}

	@Test
	void validate_InvalidNumberOfCards_InvalidUserExceptionThrown() {
		User user = new Dealer("user");
		Deck deck = new Deck(CardFactory.create());

		assertThatThrownBy(() -> user.hit(deck, 0))
			.isInstanceOf(InvalidUserException.class)
			.hasMessage(InvalidUserException.INVALID_DRAW_NUMBER);
	}

	@Test
	void calculateResultScore_UserHand_ReturnResultScore() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.TEN, Type.DIAMOND),
			Card.of(Symbol.ACE, Type.SPADE));
		User user = Dealer.valueOf(Dealer.NAME, cards);

		ResultScore expected = new ResultScore(Score.valueOf(21), ScoreType.BLACKJACK);
		assertThat(user.calculateResultScore()).isEqualTo(expected);
	}
}
