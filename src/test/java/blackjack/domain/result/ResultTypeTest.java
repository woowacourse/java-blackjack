package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Type;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

class ResultTypeTest {

	@Test
	@DisplayName("플레이어의 패배가 정상적으로 반환되는지 확인")
	void get_resultType_LOSE() {
		//given
		Dealer dealer = new Dealer();
		Player player = new Player("pobi");
		dealer.addCard(new Card(Number.NINE, Type.CLOVER));
		player.addCard(new Card(Number.EIGHT, Type.CLOVER));
		//when
		ResultType resultType = ResultType.generateResultType(player, dealer);
		//then
		assertThat(resultType).isEqualTo(ResultType.LOSE);
	}

	@Test
	@DisplayName("플레이어의 승리가 정상적으로 반환되는지 확인")
	void get_resultType_WIN() {
		//given
		Dealer dealer = new Dealer();
		Player player = new Player("pobi");
		dealer.addCard(new Card(Number.EIGHT, Type.CLOVER));
		player.addCard(new Card(Number.NINE, Type.CLOVER));
		//when
		ResultType resultType = ResultType.generateResultType(player, dealer);
		//then
		assertThat(resultType).isEqualTo(ResultType.WIN);
	}

	@Test
	@DisplayName("무승부가 정상적으로 반환되는지 확인")
	void get_resultType_DRAW() {
		//given
		Dealer dealer = new Dealer();
		Player player = new Player("pobi");
		dealer.addCard(new Card(Number.NINE, Type.CLOVER));
		player.addCard(new Card(Number.NINE, Type.HEART));
		//when
		ResultType resultType = ResultType.generateResultType(player, dealer);
		//then
		assertThat(resultType).isEqualTo(ResultType.DRAW);
	}
}
