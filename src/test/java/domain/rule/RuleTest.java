package domain.rule;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Symbol;
import domain.card.Type;
import domain.result.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RuleTest {
	private Hand hand;

	@BeforeEach
	void setUp() {
		hand = Hand.createEmpty();
		hand.add(new Card(Symbol.TEN, Type.CLUB));
	}

	@Test
	@DisplayName("블랙잭인지 확인하는 테스트")
	void isNotBlackJackTest() {
		assertThat(Rule.isNotBlackJack(hand)).isTrue();
	}

	@Test
	@DisplayName("블랙잭인지 아닌지 확인하는 테스트")
	void isBlackJackTest() {
		hand.add(new Card(Symbol.ACE, Type.HEART));
		assertThat(Rule.isBlackJack(hand)).isTrue();
	}

	@Test
	@DisplayName("버스트가 아닌지 확인하는 테스트")
	void isNotBustTest() {
		hand.add(new Card(Symbol.TEN, Type.DIAMOND));
		hand.add(new Card(Symbol.ACE, Type.DIAMOND));
		assertThat(Rule.isNotBust(hand)).isTrue();
	}

	@Test
	@DisplayName("버스트인지 확인하는 테스트")
	void isBustTest() {
		hand.add(new Card(Symbol.TEN, Type.HEART));
		hand.add(new Card(Symbol.TWO, Type.HEART));
		assertThat(Rule.isBust(hand)).isTrue();
	}

	@ParameterizedTest
	@MethodSource("generateDealerCards")
	@DisplayName("딜러가 카드를 더 받을 수 있는지 확인하는 테스트")
	void canDealerHitTest(Card card, boolean expected) {
		hand.add(card);
		assertThat(Rule.canDealerHit(hand)).isEqualTo(expected);
	}

	static Stream<Arguments> generateDealerCards() {
		return Stream.of(Arguments.of(new Card(Symbol.SIX, Type.DIAMOND), true),
				Arguments.of(new Card(Symbol.ACE, Type.DIAMOND), false));
	}

	@ParameterizedTest
	@MethodSource("generatePlayerCards")
	@DisplayName("플레이어가가 카드를 더 받을 수 있는지 확인하는 테스트")
	void canPlayerHitTest(Card card, boolean expected) {
		hand.add(card);
		assertThat(Rule.canPlayerHit(hand)).isEqualTo(expected);
	}

	static Stream<Arguments> generatePlayerCards() {
		return Stream.of(Arguments.of(new Card(Symbol.TEN, Type.DIAMOND), true),
				Arguments.of(new Card(Symbol.ACE, Type.DIAMOND), false));
	}


	@ParameterizedTest
	@MethodSource("calculateInput")
	@DisplayName("스코어 계산결과가 올바른지")
	void calculateScore(List<Card> cards, int expected) {
		Hand hand = Hand.createEmpty();
		for (Card card : cards) {
			hand.add(card);
		}

		assertThat(Rule.newScore(hand)).isEqualTo(Score.from(expected));
	}

	static Stream<Arguments> calculateInput() {
		Card ACE = new Card(Symbol.ACE, Type.CLUB);
		Card THREE = new Card(Symbol.THREE, Type.CLUB);
		Card SIX = new Card(Symbol.SIX, Type.CLUB);
		Card SEVEN = new Card(Symbol.SEVEN, Type.CLUB);
		Card EIGHT = new Card(Symbol.EIGHT, Type.CLUB);
		Card NINE = new Card(Symbol.NINE, Type.CLUB);
		Card TEN = new Card(Symbol.TEN, Type.CLUB);

		return Stream.of(Arguments.of(Arrays.asList(EIGHT, SEVEN, SIX), 21),
				Arguments.of(Arrays.asList(EIGHT, NINE), 17),
				Arguments.of(Arrays.asList(ACE, ACE, ACE), 13),
				Arguments.of(Arrays.asList(ACE, THREE), 14),
				Arguments.of(Arrays.asList(ACE, NINE, TEN), 20),
				Arguments.of(Arrays.asList(TEN, TEN, TEN), 30),
				Arguments.of(Arrays.asList(ACE, TEN, TEN, ACE), 22),
				Arguments.of(Arrays.asList(ACE, TEN, TEN), 21));
	}
}