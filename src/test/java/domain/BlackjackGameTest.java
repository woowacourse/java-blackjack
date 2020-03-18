package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.Player;

/**
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class BlackjackGameTest {
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