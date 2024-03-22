package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

	@DisplayName("카드 패의 합을 구한다.")
	@Test
	void sumCardTest() {
		// given
		Cards cards = new Cards(
			List.of(Card.of(Suit.HEART, Rank.JACK), Card.of(Suit.HEART, Rank.KING)));

		// when & then
		assertThat(cards.calculateScore()).isEqualTo(20);
	}

	@DisplayName("Ace가 포함된 카드 패의 합이 11점 초과이면, Ace는 1점이 된다.")
	@Test
	void sumAceCardAs1Test() {
		// given
		Cards cards = new Cards(
			List.of(Card.of(Suit.HEART, Rank.ACE), Card.of(Suit.HEART, Rank.KING), Card.of(Suit.HEART, Rank.JACK)));

		// when & then
		assertThat(cards.calculateScore()).isEqualTo(21);
	}

	@DisplayName("Ace가 포함된 카드 패의 합이 11점 이하이면, Ace는 11점이 된다.")
	@Test
	void sumAceCardAs11Test() {
		// given
		Cards cards = new Cards(
			List.of(Card.of(Suit.HEART, Rank.ACE), Card.of(Suit.HEART, Rank.KING)));

		// when & then
		assertThat(cards.calculateScore()).isEqualTo(21);
	}
}
