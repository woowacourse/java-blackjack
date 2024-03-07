package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.CardHand;
import domain.card.Rank;
import domain.card.Suit;

public class CardHandTest {

	@DisplayName("카드 패의 합을 구한다.")
	@Test
	void sumCardTest() {
		// given
		CardHand cardHand = CardHand.from(
			List.of(new Card(Suit.HEART, Rank.JACK), new Card(Suit.HEART, Rank.KING)));

		// when & then
		assertThat(cardHand.calculateScore()).isEqualTo(20);
	}

	@DisplayName("Ace가 포함된 카드 패의 합이 11점 초과이면, Ace는 1점이 된다.")
	@Test
	void sumAceCardAs1Test() {
		// given
		CardHand cardHand = CardHand.from(
			List.of(new Card(Suit.HEART, Rank.ACE), new Card(Suit.HEART, Rank.KING), new Card(Suit.HEART, Rank.JACK)));

		// when & then
		assertThat(cardHand.calculateScore()).isEqualTo(21);
	}

	@DisplayName("Ace가 포함된 카드 패의 합이 11점 이하이면, Ace는 11점이 된다.")
	@Test
	void sumAceCardAs11Test() {
		// given
		CardHand cardHand = CardHand.from(
			List.of(new Card(Suit.HEART, Rank.ACE), new Card(Suit.HEART, Rank.KING)));

		// when & then
		assertThat(cardHand.calculateScore()).isEqualTo(21);
	}
}
