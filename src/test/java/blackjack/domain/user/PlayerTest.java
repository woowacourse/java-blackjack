package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.blackjack.BlackjackTable;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;

class PlayerTest {
	private static Stream<Arguments> provideUndrawablePlayer() {
		final int WORST_CASE_OF_DRAWABLE_COUNT = 12;

		Player player = new Player("player");
		Deck deck = new Deck(CardFactory.create());

		for (int i = 0; i < WORST_CASE_OF_DRAWABLE_COUNT; i++) {
			player.hit(deck);
		}
		return Stream.of(Arguments.of(player));
	}

	@Test
	void Player_InputPlayerName_GenerateInstance() {
		assertThat(new Player("player")).isInstanceOf(Player.class);
	}

	@Test
	void valueOf_InputPlayerNameAndCards_GenerateInstance() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.SEVEN, Type.CLUB),
			Card.of(Symbol.TWO, Type.DIAMOND));

		assertThat(Player.valueOf("player", cards)).isInstanceOf(Player.class)
			.extracting("hand").isEqualTo(cards);
	}

	@Test
	void canDraw_CurrentScoreLowerThanDrawableMaxScore_ReturnTrue() {
		Player player = Player.valueOf("player", Arrays.asList(Card.of(Symbol.EIGHT, Type.DIAMOND)));

		assertThat(player.canDraw()).isTrue();
	}

	@ParameterizedTest
	@MethodSource("provideUndrawablePlayer")
	void canDraw_CurrentScoreMoreThanDrawableMaxScore_ReturnFalse(Player player) {
		assertThat(player.canDraw()).isFalse();
	}

	@Test
	void getInitialDealtHand_PlayerDealInitialTwoCards_HasTwoCards() {
		Deck deck = new Deck(CardFactory.create());
		Player player = new Player("player");
		player.hit(deck, BlackjackTable.INITIAL_DEAL_NUMBER);

		assertThat(player.getInitialDealtHand()).hasSize(BlackjackTable.INITIAL_DEAL_NUMBER);
	}
}
