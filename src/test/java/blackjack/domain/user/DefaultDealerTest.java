package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultDealerTest {
	private Dealer dealer;

	@BeforeEach
	void setUp() {
		dealer = DefaultDealer.create();
	}

	@Test
	void create() {
		assertThat(dealer).isNotNull();
	}

	@Test
	void shouldReceiveCard_ShouldReturnFalse() {
		// given
		dealer.giveCards(Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.SEVEN, Type.CLUB));

		// then
		assertThat(dealer.shouldReceiveCard()).isFalse();
	}

	@Test
	void shouldReceiveCard_ShouldReturnTrue() {
		// given
		dealer.giveCards(Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.SIX, Type.CLUB));

		// then
		assertThat(dealer.shouldReceiveCard()).isTrue();
	}

	@Test
	void showFirstCard() {
		// given
		dealer.giveCards(Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.SIX, Type.CLUB));

		// then
		assertThat(dealer.showFirstCard()).isEqualTo(Card.of(Symbol.TEN, Type.CLUB));
	}

	@Test
	void isWinner_ShouldReturnFalse() {
		// given
		dealer.giveCards(Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.TWO, Type.CLUB));

		// then
		assertThat(dealer.isWinner(dealer.calculateScore())).isFalse();
	}

	@Test
	void isWinner_ShouldReturnTrue() {
		// given
		dealer.giveCards(Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.TEN, Type.CLUB));

		// then
		assertThat(dealer.isWinner(dealer.calculateScore())).isTrue();
	}

	@Test
	void giveCards() {
		// when
		dealer.giveCards(Card.of(Symbol.ACE, Type.HEART),
				Card.of(Symbol.ACE, Type.CLUB));

		// then
		assertThat(dealer.countCards()).isEqualTo(2);
		assertThat(dealer.getCards()).isEqualTo(
				Arrays.asList(Card.of(Symbol.ACE, Type.HEART),
						Card.of(Symbol.ACE, Type.CLUB))
		);
	}

	@ParameterizedTest
	@MethodSource("generateCalculateScoreParams")
	void calculateScore(List<Card> cards, Score expected) {
		// given
		dealer.giveCards(cards.toArray(Card[]::new));

		// then
		assertThat(dealer.calculateScore()).isEqualTo(expected);
	}

	public static Stream<Arguments> generateCalculateScoreParams() {
		return Stream.of(
				Arguments.of(Arrays.asList(Card.of(Symbol.ACE, Type.HEART),
						Card.of(Symbol.TEN, Type.HEART)), Score.of(21)),
				Arguments.of(Arrays.asList(Card.of(Symbol.ACE, Type.HEART),
						Card.of(Symbol.ACE, Type.HEART),
						Card.of(Symbol.TEN, Type.HEART)), Score.of(12))
		);
	}

	@Test
	void isBust_ShouldReturnTrue() {
		// given
		dealer.giveCards(Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.ACE, Type.DIAMOND),
				Card.of(Symbol.ACE, Type.DIAMOND));

		// then
		assertThat(dealer.isBust()).isTrue();
	}

	@Test
	void isBust_ShouldReturnFalse() {
		// given
		dealer.giveCards(Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.ACE, Type.DIAMOND));

		// then
		assertThat(dealer.isBust()).isFalse();
	}

	@Test
	void isNotBust_ShouldReturnTrue() {
		// given
		dealer.giveCards(Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.ACE, Type.DIAMOND),
				Card.of(Symbol.ACE, Type.DIAMOND));

		// then
		assertThat(dealer.isNotBust()).isFalse();
	}

	@Test
	void isNotBust_ShouldReturnFalse() {
		// given
		dealer.giveCards(Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.ACE, Type.DIAMOND));

		// then
		assertThat(dealer.isNotBust()).isTrue();
	}

	@Test
	void getCards() {
		// given
		dealer.giveCards(Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.ACE, Type.DIAMOND));

		// then
		assertThat(dealer.getCards()).isEqualTo(Arrays.asList(
				Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.ACE, Type.DIAMOND)));
	}

	@Test
	void isName() {
		assertThat(dealer.isName("딜러")).isTrue();
	}

	@Test
	void countCards() {
		// given
		dealer.giveCards(Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.ACE, Type.DIAMOND));

		// then
		assertThat(dealer.countCards()).isEqualTo(3);
	}

	@Test
	void getName() {
		// given
		assertThat(dealer.getName()).isEqualTo("딜러");
	}
}