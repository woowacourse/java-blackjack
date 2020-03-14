package domain.user;

import static domain.card.Symbol.*;
import static domain.card.Type.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;

public class DealerTeat {
	@DisplayName("딜러의 카드가 2장이면서, 총 점수가 21점이면, 블랙잭이다.")
	@Test
	void isBlackjackTrueTest() {
		Dealer dealer = Dealer.fromCards(new Card(CLOVER, ACE), new Card(CLOVER, JACK));
		assertThat(dealer.isBlackjack()).isTrue();
	}

	@DisplayName("딜러의 카드가 2장이 아니고, 총 점수가 21점이면, 블랙잭이 아니다.")
	@Test
	void isBlackjackFalseTest() {
		Dealer dealer = Dealer.fromCards(new Card(CLOVER, ACE), new Card(CLOVER, FIVE), new Card(HEART, FIVE));
		assertThat(dealer.isBlackjack()).isFalse();
	}

	@DisplayName("딜러의 카드가 2장이고, 총 점수가 21점이 아니면, 블랙잭이 아니다.")
	@Test
	void isBlackjackFalseTest2() {
		Dealer dealer = Dealer.fromCards(new Card(CLOVER, ACE), new Card(CLOVER, FIVE));
		assertThat(dealer.isBlackjack()).isFalse();
	}

	@DisplayName("딜러의 카드가 2장이 아니고, 총 점수가 21점이 아니면, 블랙잭이 아니다.")
	@Test
	void isBlackjackFalseTest3() {
		Dealer dealer = Dealer.fromCards(new Card(CLOVER, ACE), new Card(CLOVER, FIVE), new Card(CLOVER, THREE));
		assertThat(dealer.isBlackjack()).isFalse();
	}

	@DisplayName("카드 점수 합계가 16 이하인 경우, 딜러는 카드를 한장 더 뽑아야한다.")
	@Test
	void isDrawableTest() {
		Dealer dealer = Dealer.fromCards(new Card(HEART, EIGHT), new Card(CLOVER, EIGHT));
		assertThat(dealer.isDrawable()).isTrue();
	}

	@DisplayName("카드 점수 합계가 16 초과인 경우, 딜러는 카드를 더 뽑을 수 없다.")
	@Test
	void isDrawableTest2() {
		Dealer dealer = Dealer.fromCards(new Card(HEART, EIGHT), new Card(CLOVER, NINE));
		assertThat(dealer.isDrawable()).isFalse();
	}

	@DisplayName("첫번째 드로우 카드 리스트를 가져올때, 딜러의 첫 카드 한장만 가져온다.")
	@Test
	void getInitialCardTest() {
		Dealer dealer = Dealer.fromCards(new Card(HEART, EIGHT), new Card(CLOVER, NINE));
		assertThat(dealer.getInitialCards()).containsExactly(new Card(HEART, EIGHT));
	}

	@DisplayName("딜러의 카드 점수 합계가 21점이 넘어가는 경우 버스트 상태가 된다.")
	@Test
	void isBustTrueTest() {
		Dealer dealer = Dealer.fromCards(new Card(HEART, EIGHT), new Card(CLOVER, NINE), new Card(SPADE, JACK));
		assertThat(dealer.isBust()).isTrue();
	}

	@DisplayName("딜러의 카드 점수 합계가 21점이 넘지 않는 경우 버스트 상태가 아니다.")
	@Test
	void isBustFalseTest() {
		Dealer dealer = Dealer.fromCards(new Card(HEART, EIGHT), new Card(SPADE, JACK));
		assertThat(dealer.isBust()).isFalse();
	}
}
