package domain.gamer;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GamersTest {
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
	@DisplayName("생성자 테스트")
	void createTest() {
		assertThat(gamers).isInstanceOf(Gamers.class);
	}

	@Test
	@DisplayName("모두에게 카드를 주는 기능 테스트")
	void giveCardToAllTest() {
		gamers.giveCardToAll(new Deck());
		assertThat(gamers.getDealer().getCards().size()).isEqualTo(3);
		assertThat(gamers.getPlayers().get(0).getCards().size()).isEqualTo(3);
		assertThat(gamers.getPlayers().get(1).getCards().size()).isEqualTo(3);
	}

	@Test
	@DisplayName("플레이어들을 올바르게 반환하는지 테스트")
	void getPlayersTest() {
		assertThat(gamers.getPlayers()).isEqualTo(players);
	}

	@Test
	@DisplayName("딜러를 올바르게 반환하는지 테스트")
	void getDealerTest() {
		assertThat(gamers.getDealer()).isEqualTo(dealer);
	}
}