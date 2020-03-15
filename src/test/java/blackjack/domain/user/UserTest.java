package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.card.InvalidDeckException;

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
		Deck deck = new Deck(CardFactory.create());
		user.draw(deck);

		assertThat(user).extracting("hand").asList()
			.hasOnlyElementsOfType(Card.class)
			.hasSize(1);
	}

	@Test
	void validateDeck_Null_InvalidDeckExceptionThrown() {
		User user = new Dealer("user");

		assertThatThrownBy(() -> user.draw(null))
			.isInstanceOf(InvalidDeckException.class)
			.hasMessage(InvalidDeckException.NULL);
	}

	@Test
	void draw_DrawNumberOfCards_AddDrawCardsToHand() {
		User user = new Dealer("user");
		Deck deck = new Deck(CardFactory.create());
		user.draw(deck, 2);

		assertThat(user).extracting("hand").asList()
			.hasOnlyElementsOfType(Card.class)
			.hasSize(2);
	}

	@Test
	void validateDrawNumber_InvalidNumberOfCards_InvalidUserExceptionThrown() {
		User user = new Dealer("user");
		Deck deck = new Deck(CardFactory.create());

		assertThatThrownBy(() -> user.draw(deck, 0))
			.isInstanceOf(InvalidUserException.class)
			.hasMessage(InvalidUserException.INVALID_DRAW_NUMBER);
	}
}
