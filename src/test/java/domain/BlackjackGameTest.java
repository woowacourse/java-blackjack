package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.Name;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.money.Money;

/**
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class BlackjackGameTest {
	private BlackjackGame blackjackGame;

	@BeforeEach
	void setUp() {
		blackjackGame = new BlackjackGame(new Players(
			Arrays.asList(new Name("a"), new Name("c"), new Name("b")),
			Arrays.asList(Money.of("10000"), Money.of("10000"), Money.of("10000"))));
	}

	@Test
	void testIsAllPlayerBust() {
		for (Player player : blackjackGame.getPlayers()) {
			player.hit(new Card(Type.HEART, Symbol.KING));
			player.hit(new Card(Type.SPADE, Symbol.KING));
			player.hit(new Card(Type.CLUBS, Symbol.KING));
		}

		assertThat(blackjackGame.isAllPlayersBust()).isTrue();
	}

	@Test
	void testIsDealerBlackjack() {
		blackjackGame.getDealer().hit(new Card(Type.HEART, Symbol.KING));
		blackjackGame.getDealer().hit(new Card(Type.HEART, Symbol.ACE));

		assertThat(blackjackGame.isDealerBlackjack()).isTrue();
	}

	@Test
	void Should_drawTwoCards_When_initialDraw() {
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
		blackjackGame.draw(blackjackGame.getPlayers().get(0));

		assertThat(blackjackGame.getPlayers()
			.get(0)
			.getHands()
			.getCards()
			.size()).isEqualTo(1);
	}
}