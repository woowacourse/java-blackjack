package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.Test;

import domain.gamer.Gamer;
import domain.gamer.Name;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.money.Money;

/**
 *
 *    @author AnHyungJu
 */
class GameResultTest {
	@Test
	void Should_MakeGameResult_When_GivenBlackjackGame() {
		BlackjackGame blackjackGame = new BlackjackGame(
			new Players(Arrays.asList(new Player(new Name("a"), Money.of("10000")),
				new Player(new Name("b"), Money.of("10000")), new Player(new Name("c"), Money.of("10000")))));

		assertThat(GameResult.of(blackjackGame)).isInstanceOf(GameResult.class)
			.isNotNull();
	}

	@Test
	void Given_GameResultOfBlackjackGame_when_getGameResult_Then_MapContainsPlayersAndDealer() {
		GameResult gameResult = GameResult.of(
			new BlackjackGame(new Players(Arrays.asList(new Player(new Name("a"), Money.of("10000")),
				new Player(new Name("b"), Money.of("10000")), new Player(new Name("c"), Money.of("10000"))))));

		Map<Gamer, Double> map = gameResult.getGameResult();

		assertThat(map.size()).isEqualTo(4);
	}
}