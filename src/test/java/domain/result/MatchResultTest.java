package domain.result;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Dealer;
import domain.user.Player;

public class MatchResultTest {
	Player player;
	Dealer dealer;

	@BeforeEach
	void setUp() {
		player = new Player("둔덩");
		dealer = new Dealer();
	}

	@DisplayName("플레이어가 이기는 경우")
	@Test
	void findMatchResultTest() {
		player.addCards(Arrays.asList(new Card(Symbol.CLOVER, Type.THREE)));
		dealer.addCards(Arrays.asList(new Card(Symbol.CLOVER, Type.TWO)));

		assertThat(MatchResult.findMatchResult(player, dealer))
			.isEqualTo(MatchResult.WIN);
	}

	@DisplayName("플레이어가 지는 경우")
	@Test
	void findMatchResultTest2() {
		player.addCards(Arrays.asList(new Card(Symbol.CLOVER, Type.TWO)));
		dealer.addCards(Arrays.asList(new Card(Symbol.CLOVER, Type.TWO)));

		assertThat(MatchResult.findMatchResult(player, dealer))
			.isEqualTo(MatchResult.LOSE);
	}

	@DisplayName("비기는 경우")
	@Test
	void findMatchResultTest3() {
		player.addCards(Arrays.asList(new Card(Symbol.CLOVER, Type.ACE),
			new Card(Symbol.CLOVER, Type.KING)));
		dealer.addCards(Arrays.asList(new Card(Symbol.CLOVER, Type.ACE),
			new Card(Symbol.CLOVER, Type.KING)));

		assertThat(MatchResult.findMatchResult(player, dealer))
			.isEqualTo(MatchResult.DRAW);
	}
}
