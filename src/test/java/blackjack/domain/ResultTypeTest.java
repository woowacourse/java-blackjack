package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResultTypeTest {

	@Test
	void get_resultType_LOSE() {
		//given
		Dealer dealer = new Dealer();
		Player player = new Player("pobi");
		dealer.processCard(new Card(Number.NINE, Type.CLOVER));
		player.processCard(new Card(Number.EIGHT, Type.CLOVER));
		//when
		ResultType resultType = ResultType.generateResultType(player, dealer);
		//then
		assertThat(resultType).isEqualTo(ResultType.LOSE);
	}

	@Test
	void get_resultType_WIN() {
		//given
		Dealer dealer = new Dealer();
		Player player = new Player("pobi");
		dealer.processCard(new Card(Number.EIGHT, Type.CLOVER));
		player.processCard(new Card(Number.NINE, Type.CLOVER));
		//when
		ResultType resultType = ResultType.generateResultType(player, dealer);
		//then
		assertThat(resultType).isEqualTo(ResultType.WIN);
	}

	@Test
	void get_resultType_DRAW() {
		//given
		Dealer dealer = new Dealer();
		Player player = new Player("pobi");
		dealer.processCard(new Card(Number.NINE, Type.CLOVER));
		player.processCard(new Card(Number.NINE, Type.HEART));
		//when
		ResultType resultType = ResultType.generateResultType(player, dealer);
		//then
		assertThat(resultType).isEqualTo(ResultType.DRAW);
	}
}
