package domain.result;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {
	private List<Player> players;
	private Dealer dealer;
	private Gamers gamers;

	@BeforeEach
	void setUp() {
		players = Arrays.asList(new Player(new Name("pobi"), new Money(10000)),
				new Player(new Name("jason"), new Money(5000)),
				new Player(new Name("brwon"), new Money(3000)));
		dealer = new Dealer();

		players.get(0).hit(new Card(Symbol.TWO, Type.CLUB));
		players.get(0).hit(new Card(Symbol.ACE, Type.CLUB));
		players.get(1).hit(new Card(Symbol.NINE, Type.CLUB));
		players.get(1).hit(new Card(Symbol.TEN, Type.CLUB));
		players.get(2).hit(new Card(Symbol.TEN, Type.CLUB));
		players.get(2).hit(new Card(Symbol.ACE, Type.CLUB));
		dealer.hit(new Card(Symbol.EIGHT, Type.CLUB));
		dealer.hit(new Card(Symbol.SEVEN, Type.CLUB));

		gamers = new Gamers(players, dealer);
	}

	@Test
	@DisplayName("플레이어의 수익이 올바르게 계산되는 확인")
	void gamerProfitTest() {
		GameResult gameResult = new GameResult(gamers);

		Map<Gamer, Profit> expected = new HashMap<>();
		expected.put(players.get(0), new Profit(-10000));
		expected.put(players.get(1), new Profit(5000));
		expected.put(players.get(2), new Profit(1500));
		expected.put(dealer, new Profit(3500));

		assertThat(gameResult.getGamersProfit()).isEqualTo(expected);
	}

	@Test
	@DisplayName("게이머들의 스코어가 올바르게 생성되는지 확인")
	void gamersScoreTest() {
		GameResult gameResult = new GameResult(gamers);

		Map<Gamer, Score> expected = new HashMap<>();
		expected.put(players.get(0), players.get(0).getScore());
		expected.put(players.get(1), players.get(1).getScore());
		expected.put(players.get(2), players.get(2).getScore());
		expected.put(dealer, dealer.getScore());

		assertThat(gameResult.getGamersScore()).isEqualTo(expected);
	}
}