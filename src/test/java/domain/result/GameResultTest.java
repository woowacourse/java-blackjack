package domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.participant.Dealer;
import domain.participant.Player;

class GameResultTest {
	private List<Player> players;
	private Dealer dealer;

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
	}

	@Test
	@DisplayName("게임 결과가 올바르게 생성되는지 확인")
	void fromTest() {
		GameResult gameResult = GameResult.from(players, dealer);

		Map<Player, Result> expected = new HashMap<>();
		expected.put(players.get(0), Result.LOSE);
		expected.put(players.get(1), Result.WIN);

		assertThat(gameResult.getPlayersResult()).isEqualTo(expected);
	}

	@Test
	@DisplayName("딜러의 결과가 올바르게 생성되는지 확인")
	void dealerResultTest() {
		GameResult gameResult = GameResult.from(players, dealer);

		Map<Result, Integer> expected = new HashMap<>();
		expected.put(Result.LOSE, 1);
		expected.put(Result.WIN, 1);

		assertThat(gameResult.dealerResult()).isEqualTo(expected);

	}
}