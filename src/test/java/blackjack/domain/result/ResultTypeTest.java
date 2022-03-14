package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

class ResultTypeTest {

	@Test
	@DisplayName("플레이어의 패배가 정상적으로 반환되는지 확인")
	void get_resultType_LOSE() {
		//given
		Dealer dealer = new Dealer();
		Player player = new Player("pobi");
		dealer.addCard(new Card(Denomination.NINE, Suit.CLOVER));
		player.addCard(new Card(Denomination.EIGHT, Suit.CLOVER));
		//when
		ResultType resultType = ResultType.getMatchedResultType(player, dealer);
		//then
		assertThat(resultType).isEqualTo(ResultType.LOSE);
	}

	@Test
	@DisplayName("플레이어의 승리가 정상적으로 반환되는지 확인")
	void get_resultType_WIN() {
		//given
		Dealer dealer = new Dealer();
		Player player = new Player("pobi");
		dealer.addCard(new Card(Denomination.EIGHT, Suit.CLOVER));
		player.addCard(new Card(Denomination.NINE, Suit.CLOVER));
		//when
		ResultType resultType = ResultType.getMatchedResultType(player, dealer);
		//then
		assertThat(resultType).isEqualTo(ResultType.WIN);
	}

	@Test
	@DisplayName("무승부가 정상적으로 반환되는지 확인")
	void get_resultType_DRAW() {
		//given
		Dealer dealer = new Dealer();
		Player player = new Player("pobi");
		dealer.addCard(new Card(Denomination.NINE, Suit.CLOVER));
		player.addCard(new Card(Denomination.NINE, Suit.HEART));
		//when
		ResultType resultType = ResultType.getMatchedResultType(player, dealer);
		//then
		assertThat(resultType).isEqualTo(ResultType.DRAW);
	}

	@Test
	@DisplayName("딜러가 블랙잭이고 플레이어가 블랙잭이 아닌 21 점수인 경우 플레이어 패배")
	void get_resultType_dealer_blackjack() {
		//given
		Dealer dealer = new Dealer();
		Player player = new Player("pobi");
		dealer.addCard(new Card(Denomination.ACE, Suit.CLOVER));
		dealer.addCard(new Card(Denomination.JACK, Suit.HEART));
		player.addCard(new Card(Denomination.ACE, Suit.HEART));
		player.addCard(new Card(Denomination.EIGHT, Suit.HEART));
		player.addCard(new Card(Denomination.TWO, Suit.HEART));
		//when
		ResultType resultType = ResultType.getMatchedResultType(player, dealer);
		//given
		assertThat(resultType).isEqualTo(ResultType.LOSE);
	}

	@Test
	@DisplayName("딜러가 블랙잭이고 플레이어도 블랙잭인 경우 무승부")
	void get_resultType_dealer_and_player_blackjack() {
		//given
		Dealer dealer = new Dealer();
		Player player = new Player("pobi");
		dealer.addCard(new Card(Denomination.ACE, Suit.CLOVER));
		dealer.addCard(new Card(Denomination.JACK, Suit.HEART));
		player.addCard(new Card(Denomination.ACE, Suit.HEART));
		player.addCard(new Card(Denomination.QUEEN, Suit.HEART));
		//when
		ResultType resultType = ResultType.getMatchedResultType(player, dealer);
		//given
		assertThat(resultType).isEqualTo(ResultType.DRAW);
	}
}
