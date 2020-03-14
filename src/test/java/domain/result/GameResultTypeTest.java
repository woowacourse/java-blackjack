package domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.gamer.Gamer;
import domain.gamer.Gamers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.Dealer;
import domain.gamer.Player;

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
	@DisplayName("게임 결과가 올바르게 생성되는지 확인")
	void fromTest() {
		GameResult gameResult = new GameResult(gamers);

		Map<Player, ResultType> expected = new HashMap<>();
		expected.put(players.get(0), ResultType.LOSE);
		expected.put(players.get(1), ResultType.WIN);

		assertThat(gameResult.playersResult()).isEqualTo(expected);
	}

	@Test
	@DisplayName("딜러의 결과가 올바르게 생성되는지 확인")
	void dealerResultTest() {
		GameResult gameResult = new GameResult(gamers);

		Map<ResultType, Integer> expected = new HashMap<>();
		expected.put(ResultType.LOSE, 1);
		expected.put(ResultType.WIN, 1);

		assertThat(gameResult.dealerResult()).isEqualTo(expected);

	}

	@Test
	@DisplayName("게이머들의 스코어가 올바르게 생성되는지 확인")
	void gamersScoreTest() {
		GameResult gameResult = new GameResult(gamers);

		Map<Gamer, Score> expected = new HashMap<>();
		expected.put(players.get(0), players.get(0).calculateScore());
		expected.put(players.get(1), players.get(1).calculateScore());
		expected.put(dealer, dealer.calculateScore());

		assertThat(gameResult.gamersScore()).isEqualTo(expected);
	}
}