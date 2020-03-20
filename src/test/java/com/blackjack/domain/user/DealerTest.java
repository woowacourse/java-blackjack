package com.blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.domain.Score;
import com.blackjack.domain.card.Card;
import com.blackjack.domain.card.CardNumber;
import com.blackjack.domain.card.CardSymbol;

class DealerTest {
	@DisplayName("생성자를 통해 딜러의 인스턴스가 생성됨")
	@Test
	void constructor() {
		assertThat(new Dealer()).isInstanceOf(Dealer.class);
	}

	@DisplayName("가지고 있는 카드가 16 이하여서 카드를 더 뽑을 수 있는 경우 true를 반환한다")
	@Test
	void canDraw_GivenCard_ReturnTrue() {
		Dealer dealer = new Dealer();
		dealer.draw(Card.valueOf(CardNumber.ACE, CardSymbol.CLUB));

		assertThat(dealer.getHand().getCards().size()).isEqualTo(1);
	}

	@DisplayName("가지고 있는 카드가 17 이상이여서 카드를 더 뽑을 수 없는 경우 false를 반환한다")
	@Test
	void canDraw_OverThenSixteenScore_ReturnFalse() {
		Dealer dealer = new Dealer();

		dealer.draw(Card.valueOf(CardNumber.TEN, CardSymbol.CLUB));
		dealer.draw(Card.valueOf(CardNumber.TEN, CardSymbol.CLUB));

		assertThat(dealer.canDraw()).isEqualTo(false);
	}

	@DisplayName("가지고 있는 카드가 21 이상이 되어 점수가 버스트가 됨")
	@Test
	void canDraw_OverThenBlackjackScore_GenerateBustScore() {
		Dealer dealer = new Dealer();

		dealer.draw(Card.valueOf(CardNumber.JACK, CardSymbol.CLUB));
		dealer.draw(Card.valueOf(CardNumber.TEN, CardSymbol.CLUB));
		dealer.draw(Card.valueOf(CardNumber.TEN, CardSymbol.HEART));

		Score score = dealer.calculateHand();
		assertThat(score.isBust()).isTrue();
	}
}
