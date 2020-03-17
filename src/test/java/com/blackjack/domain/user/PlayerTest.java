package com.blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.domain.card.Card;
import com.blackjack.domain.card.CardDeck;
import com.blackjack.domain.card.Denomination;
import com.blackjack.domain.card.Suit;

class PlayerTest {
	@DisplayName("가지고 있는 카드가 20 이하여서 카드를 더 뽑을 수 있는 경우 true를 반환한다")
	@Test
	void canDraw_GivenCard_ReturnTrue() {
		List<Card> cards = Arrays.asList(
				Card.valueOf(Denomination.TEN, Suit.SPADE),
				Card.valueOf(Denomination.TEN, Suit.CLUB));
		CardDeck cardDeck = CardDeck.create(cards);
		Player player = new Player(new Name("pobi"));

		player.draw(cardDeck);
		player.draw(cardDeck);

		assertThat(player.canDraw()).isTrue();
	}

	@DisplayName("가지고 있는 카드가 21 이상이여서 카드를 더 뽑을 수 있는 경우 false를 반환한다")
	@Test
	void canDraw_OverThenSixteenScore_ReturnFalse() {
		List<Card> cards = Arrays.asList(
				Card.valueOf(Denomination.JACK, Suit.CLUB),
				Card.valueOf(Denomination.TEN, Suit.CLUB),
				Card.valueOf(Denomination.KING, Suit.CLUB));
		CardDeck cardDeck = CardDeck.create(cards);
		Player player = new Player(new Name("pobi"));

		player.drawAtFirst(cardDeck);
		player.draw(cardDeck);

		assertThat(player.canDraw()).isFalse();
	}
}
