package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.Player;

/**
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class BlackjackGameTest {
	@ParameterizedTest
	@NullAndEmptySource
	void Should_ThrownException_When_NameIsNull_OrEmpty(String[] playersName) {
		assertThatThrownBy(() -> new BlackjackGame(playersName))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}

	@Test
	void Should_ThrownException_When_NameIsDuplicated() {
		String[] playersName = {"a", "a", "b"};
		assertThatThrownBy(() -> new BlackjackGame(playersName))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("중복");
	}

	@Test
	void Should_ThrownException_When_Overmanned() {
		String[] playersName = {"a", "b", "c", "d", "e", "gg"};
		assertThatThrownBy(() -> new BlackjackGame(playersName))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("초과");
	}

	@Test
	void testIsAllPlayerBust() {
		BlackjackGame blackjackGame = new BlackjackGame(new String[] {"a", "b"});

		for (Player player : blackjackGame.getPlayers()) {
			player.hit(new Card(Type.HEART, Symbol.KING));
			player.hit(new Card(Type.SPADE, Symbol.KING));
			player.hit(new Card(Type.CLUBS, Symbol.KING));
		}

		assertThat(blackjackGame.isAllPlayersBust()).isTrue();
	}

	@Test
	void testIsDealerBlackjack() {
		BlackjackGame blackjackGame = new BlackjackGame(new String[] {"a"});

		blackjackGame.getDealer().hit(new Card(Type.HEART, Symbol.KING));
		blackjackGame.getDealer().hit(new Card(Type.HEART, Symbol.ACE));

		assertThat(blackjackGame.isDealerBlackjack()).isTrue();
	}

	@Test
	void Should_drawTwoCards_When_initialDraw() {
		BlackjackGame blackjackGame = new BlackjackGame(new String[] {"a", "b"});

		blackjackGame.initialDraw();

		assertThat(blackjackGame.getDealer()
			.getHands()
			.getCards()
			.size()).isEqualTo(2);
		blackjackGame.getPlayers().forEach(player -> assertThat(player.getHands()
			.getCards()
			.size()).isEqualTo(2)
		);
	}

	@Test
	void Should_drawOneCard_When_Draw() {
		BlackjackGame blackjackGame = new BlackjackGame(new String[] {"a"});

		blackjackGame.draw(blackjackGame.getPlayers().get(0));

		assertThat(blackjackGame.getPlayers()
			.get(0)
			.getHands()
			.getCards()
			.size()).isEqualTo(1);
	}
}