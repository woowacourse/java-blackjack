package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Number;
import blackjack.domain.card.Type;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Gamer;
import blackjack.domain.user.Player;

class GamerTest {
	@Test
	@DisplayName("카드 분배 기능 이용했을 때 게이머의 패가 1장 늘어나는지 확인")
	void distribute_card() {
		//given
		Gamer gamer = new Player("pobi");
		Deck deck = new Deck();
		//when
		gamer.addCard(deck.distributeCard());
		//then
		System.out.println(gamer);
		assertThat(gamer.getCards().size()).isEqualTo(1);
	}

	@Test
	@DisplayName("버스트인지 확인하는 기능이 정상 작동하는지 확인")
	void isBurst() {
		//given
		Gamer gamer = new Dealer();
		//when
		gamer.addCard(new Card(Number.TEN, Type.CLOVER));
		gamer.addCard(new Card(Number.TEN, Type.HEART));
		gamer.addCard(new Card(Number.TWO, Type.SPADE));
		//then
		// System.out.println(gamer.getScore());
		assertThat(gamer.isBurst()).isTrue();
	}

	@Test
	@DisplayName("에이스가 포함된 패일 경우 최적의 스코어가 반환되는지 확인")
	void check_optimal_ace_sum() {
		// given
		Gamer gamer = new Player("pobi");
		gamer.addCard(new Card(Number.ACE, Type.HEART));
		gamer.addCard(new Card(Number.ACE, Type.SPADE));
		gamer.addCard(new Card(Number.NINE, Type.SPADE));
		// when
		// then
		assertThat(gamer).extracting("score").isEqualTo(21);
	}
}
