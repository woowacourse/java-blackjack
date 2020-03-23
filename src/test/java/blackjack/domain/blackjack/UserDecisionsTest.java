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
		BiConsumer<User, List<Card>> showHandStatus = (User user, List<Card> cards) -> System.out.println("test");
		PrintChoice showDealerHitStatus = () -> System.out.println("test");

		assertThat(new UserDecisions(choice, showHandStatus, showDealerHitStatus)).isInstanceOf(UserDecisions.class);
	}

	@Test
	void validate_NullConstructorArguments_InvalidUserDecisionsExceptionThrown() {
		Function<Player, String> choice = (Player player) -> "y";
		BiConsumer<User, List<Card>> showHandStatus = (User user, List<Card> cards) -> System.out.println("test");
		PrintChoice showDealerHitStatus = () -> System.out.println("test");

		assertThatThrownBy(() -> new UserDecisions(null, showHandStatus, showDealerHitStatus))
			.isInstanceOf(InvalidUserDecisionsException.class)
			.hasMessage(InvalidUserDecisionsException.USER_DECISIONS_NULL);
		assertThatThrownBy(() -> new UserDecisions(choice, null, showDealerHitStatus))
			.isInstanceOf(InvalidUserDecisionsException.class)
			.hasMessage(InvalidUserDecisionsException.USER_DECISIONS_NULL);
		assertThatThrownBy(() -> new UserDecisions(choice, showHandStatus, null))
			.isInstanceOf(InvalidUserDecisionsException.class)
			.hasMessage(InvalidUserDecisionsException.USER_DECISIONS_NULL);
	}

	@Test
	void validate_NullPlayer_InvalidUserDecisionsExceptionThrown() {
		Function<Player, String> choice = (Player player) -> "y";
		BiConsumer<User, List<Card>> showHandStatus = (User user, List<Card> cards) -> System.out.println("test");
		PrintChoice showDealerHitStatus = () -> System.out.println("test");
		UserDecisions userDecisions = new UserDecisions(choice, showHandStatus, showDealerHitStatus);

		assertThatThrownBy(() -> userDecisions.isHit(null))
			.isInstanceOf(InvalidUserDecisionsException.class)
			.hasMessage(InvalidUserDecisionsException.PLAYER_NULL);
		assertThatThrownBy(() -> userDecisions.showHandStatus(null))
			.isInstanceOf(InvalidUserDecisionsException.class)
			.hasMessage(InvalidUserDecisionsException.PLAYER_NULL);
	}

	@Test
	void isHit_InputUserDecisionsHitPlayer_ReturnTrue() {
		Player testPlayer = new Player("player");
		Function<Player, String> choice = (Player player) -> "y";
		BiConsumer<User, List<Card>> showHandStatus = (User user, List<Card> cards) -> System.out.println("test");
		PrintChoice showDealerHitStatus = () -> System.out.println("test");
		UserDecisions userDecisions = new UserDecisions(choice, showHandStatus, showDealerHitStatus);

		assertThat(userDecisions.isHit(testPlayer)).isTrue();
	}
}
