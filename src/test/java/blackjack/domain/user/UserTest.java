package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

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

	@Test
	void draw_DrawCard_AddDrawCardToHand() {
		User user = new Dealer("user");
		Deck deck = new Deck();
		user.draw(deck);

		assertThat(user).extracting("hand").asList()
			.hasOnlyElementsOfType(Card.class)
			.hasSize(1);
	}

	@Test
	void draw_DrawNumberOfCards_AddDrawCardsToHand() {
		User user = new Dealer("user");
		Deck deck = new Deck();
		user.draw(deck, 2);

		assertThat(user).extracting("hand").asList()
			.hasOnlyElementsOfType(Card.class)
			.hasSize(2);
	}

	@Test
	void validateDrawNumber_InvalidNumberOfCards_InvalidUserExceptionThrown() {
		User user = new Dealer("user");
		Deck deck = new Deck();

		assertThatThrownBy(() -> user.draw(deck, 0))
			.isInstanceOf(InvalidUserException.class)
			.hasMessage(InvalidUserException.INVALID_DRAW_NUMBER);
	}

	@ParameterizedTest
	@MethodSource("provideBustUser")
	void isBust_BustScoreUser_ReturnTrue(User user) {
		assertThat(user.isBust()).isTrue();
	}

	private static Stream<Arguments> provideBustUser() {
		final int WORST_CASE_OF_DRAWABLE_COUNT = 12;

		User user = new Player("user");
		Deck deck = new Deck();

		for (int i = 0; i < WORST_CASE_OF_DRAWABLE_COUNT; i++) {
			user.draw(deck);
		}
		return Stream.of(Arguments.of(user));
	}
}
