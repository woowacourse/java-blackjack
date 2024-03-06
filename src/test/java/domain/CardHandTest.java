package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardHandTest {

	@DisplayName("카드 패의 합을 구한다.")
	@Test
	void sumCardTest() {
		// given
		CardHand cardHand = new CardHand(
			List.of(new Card(Suit.HEART, Rank.J), new Card(Suit.HEART, Rank.K)));

		// when & then
		assertThat(cardHand.calculateScore()).isEqualTo(20);
	}

	@DisplayName("A가 포함된 카드 패의 합이 11점 초과이면, A는 1점이 된다.")
	@Test
	void sumACardAs1Test() {
		// given
		CardHand cardHand = new CardHand(
			List.of(new Card(Suit.HEART, Rank.A), new Card(Suit.HEART, Rank.K), new Card(Suit.HEART, Rank.J)));

		// when & then
		assertThat(cardHand.calculateScore()).isEqualTo(21);
	}

	@DisplayName("A가 포함된 카드 패의 합이 11점 이하이면, A는 11점이 된다.")
	@Test
	void sumACardAs11Test() {
		// given
		CardHand cardHand = new CardHand(
			List.of(new Card(Suit.HEART, Rank.A), new Card(Suit.HEART, Rank.K)));

		// when & then
		assertThat(cardHand.calculateScore()).isEqualTo(21);
	}
}
