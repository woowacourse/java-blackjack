package com.blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.domain.BettingMoney;
import com.blackjack.domain.ResultType;
import com.blackjack.domain.card.Card;
import com.blackjack.domain.card.CardNumber;
import com.blackjack.domain.card.CardSymbol;

class PlayerTest {
	@DisplayName("베팅 금액이 null인 경우 예외 발생")
	@Test
	void constructor_BettingMoneyIsNull_ExceptionThrown() {
		assertThatThrownBy(() -> new Player(new Name("pobi"), null))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("플레이어명이 null인 경우 예외 발생")
	@Test
	void constructor_PlayerNameIsNull_ExceptionThrown() {
		assertThatThrownBy(() -> new Player(null)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("가지고 있는 카드가 20 이하여서 카드를 더 뽑을 수 있는 경우 true를 반환한다")
	@Test
	void canDraw_GivenCard_ReturnTrue() {
		Player player = new Player(new Name("pobi"));

		player.draw(Card.valueOf(CardNumber.TEN, CardSymbol.SPADE));
		player.draw(Card.valueOf(CardNumber.TEN, CardSymbol.CLUB));

		assertThat(player.canDraw()).isTrue();
	}

	@DisplayName("가지고 있는 카드가 21 이상이여서 카드를 더 뽑을 수 있는 경우 false를 반환한다")
	@Test
	void canDraw_OverThenSixteenScore_False() {
		Player player = new Player(new Name("pobi"));

		player.draw(Card.valueOf(CardNumber.JACK, CardSymbol.CLUB));
		player.draw(Card.valueOf(CardNumber.TEN, CardSymbol.CLUB));
		player.draw(Card.valueOf(CardNumber.KING, CardSymbol.CLUB));

		assertThat(player.canDraw()).isFalse();
	}

	@DisplayName("플레이어가 승패 결과를 받아 수익률 계산")
	@Test
	void calculateProfit() {
		Player player = new Player("pobi", 1_000);
		assertThat(player.calculateProfit(ResultType.BLACKJACK_WIN)).isEqualTo(1_500);
	}
}
