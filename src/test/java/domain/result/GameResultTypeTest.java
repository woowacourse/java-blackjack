package domain.result;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTypeTest {
	private List<Player> players;
	private Dealer dealer;
	private Gamers gamers;

	@BeforeEach
	void setUp() {
		players = Arrays.asList(new Player("pobi"), new Player("jason"));
		dealer = new Dealer();

		players.get(0).hit(new Card(Symbol.TWO, Type.CLUB));
		players.get(0).hit(new Card(Symbol.ACE, Type.CLUB));
		players.get(1).hit(new Card(Symbol.NINE, Type.CLUB));
		players.get(1).hit(new Card(Symbol.TEN, Type.CLUB));
		dealer.hit(new Card(Symbol.EIGHT, Type.CLUB));
		dealer.hit(new Card(Symbol.SEVEN, Type.CLUB));

		gamers = new Gamers(players, dealer);
	}

	@Test
	@DisplayName("플레이어의 결과가 올바르게 생성되는지 확인")
	void fromTest() {
		GameResult gameResult = new GameResult(gamers);

		Map<Player, ResultType> expected = new HashMap<>();
		expected.put(players.get(0), ResultType.LOSE);
		expected.put(players.get(1), ResultType.WIN);

		assertThat(gameResult.getPlayersResult()).isEqualTo(expected);
	}

	@Test
	@DisplayName("딜러의 결과가 올바르게 생성되는지 확인")
	void dealerResultTest() {
		GameResult gameResult = new GameResult(gamers);

		Map<ResultType, Integer> expected = new HashMap<>();
		expected.put(ResultType.LOSE, 1);
		expected.put(ResultType.WIN, 1);

		assertThat(gameResult.getDealerResult()).isEqualTo(expected);

	}

	@Test
	@DisplayName("게이머들의 스코어가 올바르게 생성되는지 확인")
	void gamersScoreTest() {
		GameResult gameResult = new GameResult(gamers);

		Map<Gamer, Score> expected = new HashMap<>();
		expected.put(players.get(0), players.get(0).getScore());
		expected.put(players.get(1), players.get(1).getScore());
		expected.put(dealer, dealer.getScore());

		assertThat(gameResult.getGamersScore()).isEqualTo(expected);
	}
}