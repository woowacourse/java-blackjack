package com.blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.domain.Score;
import com.blackjack.domain.card.Card;
import com.blackjack.domain.card.CardDeck;
import com.blackjack.domain.card.Denomination;
import com.blackjack.domain.card.Suit;

class DealerTest {
	@DisplayName("생성자를 통해 딜러의 인스턴스가 생성됨")
	@Test
	void constructor() {
		assertThat(new Dealer()).isInstanceOf(Dealer.class);
	}

	@DisplayName("가지고 있는 카드가 16 이하여서 카드를 더 뽑을 수 있는 경우 true를 반환한다")
	@Test
	void canDraw_GivenCard_ReturnTrue() {
		List<Card> cards = Collections.singletonList(Card.valueOf(Denomination.ACE, Suit.CLUB));
		CardDeck cardDeck = CardDeck.create(cards);
		Dealer dealer = new Dealer();

		dealer.draw(cardDeck);

		assertThat(dealer.getHands().getCards().size()).isEqualTo(1);
	}

	@DisplayName("가지고 있는 카드가 17 이상이여서 카드를 더 뽑을 수 있는 경우 false를 반환한다")
	@Test
	void canDraw_OverThenSixteenScore_ReturnFalse() {
		List<Card> cards = Arrays.asList(
				Card.valueOf(Denomination.JACK, Suit.CLUB),
				Card.valueOf(Denomination.TEN, Suit.CLUB));
		CardDeck cardDeck = CardDeck.create(cards);
		Dealer dealer = new Dealer();

		dealer.draw(cardDeck);
		dealer.draw(cardDeck);

		assertThat(dealer.canDraw()).isEqualTo(false);
	}

	@DisplayName("가지고 있는 카드가 21 이상이 되어 점수가 버스트가 됨")
	@Test
	void canDraw_OverThenBlackjackScore_GenerateBustScore() {
		List<Card> cards = Arrays.asList(
				Card.valueOf(Denomination.JACK, Suit.CLUB),
				Card.valueOf(Denomination.TEN, Suit.CLUB),
				Card.valueOf(Denomination.TEN, Suit.HEART));
		CardDeck cardDeck = CardDeck.create(cards);
		Dealer dealer = new Dealer();

		dealer.draw(cardDeck);
		dealer.draw(cardDeck);
		dealer.draw(cardDeck);

		assertThat(dealer.hands.calculateScore()).isEqualTo(Score.valueOf(0));
	}
}
