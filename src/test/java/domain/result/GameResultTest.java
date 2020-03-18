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
import domain.gamer.Dealer;
import domain.gamer.Money;
import domain.gamer.Name;
import domain.gamer.Player;

class GameResultTest {
	private List<Player> players;
	private Dealer dealer;

	@BeforeEach
	void setUp() {
		players = Arrays.asList(new Player(new Name("pobi"), Money.of(10000)),
			new Player(new Name("jason"), Money.of(5000)));
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
		GameResult gameResult = GameResult.of(players, dealer);
		Map<Player, Profit> expected = new HashMap<>();
		expected.put(players.get(0), new Profit(-10000));
		expected.put(players.get(1), new Profit(5000));

		assertThat(gameResult.getPlayerToProfit()).isEqualTo(expected);
	}

	@Test
	@DisplayName("딜러의 결과가 올바르게 생성되는지 확인")
	void dealerResultTest() {
		GameResult gameResult = GameResult.of(players, dealer);

		assertThat(gameResult.dealerResult()).isEqualTo(new Profit(5000));
	}
}