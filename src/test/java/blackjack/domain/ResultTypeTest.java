package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResultTypeTest {

	@Test
	void get_resultType_LOSE() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(Number.NINE, Type.CLOVER));
		player.processCard(new Card(Number.EIGHT, Type.CLOVER));
		ResultType resultType = ResultType.generateResultType(player, dealer);
		assertThat(resultType).isEqualTo(ResultType.LOSE);
	}

	@Test
	void get_resultType_WIN() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(Number.EIGHT, Type.CLOVER));
		player.processCard(new Card(Number.NINE, Type.CLOVER));
		ResultType resultType = ResultType.generateResultType(player, dealer);
		assertThat(resultType).isEqualTo(ResultType.WIN);
	}

	@Test
	void get_resultType_DRAW() {
		Dealer dealer = new Dealer();
		Player player = new Player(new Name("pobi"));
		dealer.processCard(new Card(Number.NINE, Type.CLOVER));
		player.processCard(new Card(Number.NINE, Type.HEART));
		ResultType resultType = ResultType.generateResultType(player, dealer);
		assertThat(resultType).isEqualTo(ResultType.DRAW);
	}
}
