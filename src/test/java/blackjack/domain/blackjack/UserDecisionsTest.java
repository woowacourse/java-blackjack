package blackjack.domain.blackjack;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.exceptions.InvalidUserDecisionsException;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;

class UserDecisionsTest {
	@Test
	void UserDecisions_InputFunctionAndBiConsumerAndRunnable_GenerateInstance() {
		Function<Player, String> choice = (Player player) -> "y";
		BiConsumer<User, List<Card>> handStatus = (User user, List<Card> cards) -> System.out.println("test");
		Runnable dealerChoice = () -> System.out.println("test");

		assertThat(new UserDecisions(choice, handStatus, dealerChoice)).isInstanceOf(UserDecisions.class);
	}

	@Test
	void validate_NullSource_InvalidUserDecisionsExceptionThrown() {
		Function<Player, String> choice = (Player player) -> "y";
		BiConsumer<User, List<Card>> handStatus = (User user, List<Card> cards) -> System.out.println("test");
		Runnable dealerChoice = () -> System.out.println("test");

		assertThatThrownBy(() -> new UserDecisions(null, handStatus, dealerChoice))
			.isInstanceOf(InvalidUserDecisionsException.class)
			.hasMessage(InvalidUserDecisionsException.NULL);
		assertThatThrownBy(() -> new UserDecisions(choice, null, dealerChoice))
			.isInstanceOf(InvalidUserDecisionsException.class)
			.hasMessage(InvalidUserDecisionsException.NULL);
		assertThatThrownBy(() -> new UserDecisions(choice, handStatus, null))
			.isInstanceOf(InvalidUserDecisionsException.class)
			.hasMessage(InvalidUserDecisionsException.NULL);
	}

	@Test
	void isHit_InputUserDecisionsHitPlayer_ReturnTrue() {
		Player testPlayer = new Player("player");
		Function<Player, String> choice = (Player player) -> "y";
		BiConsumer<User, List<Card>> handStatus = (User user, List<Card> cards) -> System.out.println("test");
		Runnable dealerChoice = () -> System.out.println("test");
		UserDecisions userDecisions = new UserDecisions(choice, handStatus, dealerChoice);

		assertThat(userDecisions.isHit(testPlayer)).isTrue();
	}
}
