package blackjack.domain.card;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
	private static Card aceSpade;
	private static Card twoClub;
	private static Card sixDiamond;
	private static Card tenClub;
	private static Card jackHeart;

	private Hand hand;

	@BeforeAll
	static void beforeAll() {
		aceSpade = Card.of(Symbol.ACE, Type.SPADE);
		twoClub = Card.of(Symbol.TWO, Type.CLUB);
		sixDiamond = Card.of(Symbol.SIX, Type.DIAMOND);
		tenClub = Card.of(Symbol.TEN, Type.CLUB);
		jackHeart = Card.of(Symbol.JACK, Type.HEART);
	}

	@BeforeEach
	void setUp() {
		hand = new Hand();
	}

	@DisplayName("Hand()가 인스턴스를 반환하는지 테스트")
	@Test
	void Hand_IsNotNull() {
		assertThat(hand).isNotNull();
	}

	@DisplayName("add()가 hand에 카드를 추가하는지 테스트")
	@ParameterizedTest
	@MethodSource("add_Card_AddCardToHand")
	void add_Card_AddCardToHand(Card card) {
		hand.add(card);
		assertThat(hand.getHand()).isEqualTo(Collections.singletonList(card));
	}

	static Stream<Card> add_Card_AddCardToHand() {
		return Stream.of(aceSpade, twoClub, sixDiamond, tenClub, jackHeart);
	}

	@DisplayName("add()를 두 번 호출했을때 적절한 카드가 두 장 들어가는지 테스트")
	@ParameterizedTest
	@MethodSource("add_TwoCards_AddTwoCardsToHand")
	void add_TwoCards_AddTwoCardsToHand(List<Card> cards) {
		for (Card card : cards) {
			hand.add(card);
		}
		assertThat(hand.getHand()).isEqualTo(cards);
	}

	static Stream<List<Card>> add_TwoCards_AddTwoCardsToHand() {
		return Stream.of(Arrays.asList(aceSpade, twoClub),
				Arrays.asList(sixDiamond, tenClub),
				Arrays.asList(jackHeart, jackHeart));
	}

	@DisplayName("addAll()가 카드를 추가하는지 테스트")
	@ParameterizedTest
	@MethodSource("addAll_Cards_AddAllToHand")
	void addAll_Cards_AddAllToHand(List<Card> cards) {
		hand.addAll(cards);
		assertThat(hand.getHand()).isEqualTo(cards);
	}

	static Stream<List<Card>> addAll_Cards_AddAllToHand() {
		return Stream.of(Collections.emptyList(),
				Collections.singletonList(aceSpade),
				Arrays.asList(sixDiamond, tenClub),
				Arrays.asList(aceSpade, sixDiamond, tenClub, twoClub, jackHeart, jackHeart));
	}

	@DisplayName("computeScore()가 점수를 계산하여 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("getScore")
	void getScore(List<Card> cards, int score) {
		hand.addAll(cards);
		assertThat(hand.computeScore()).isEqualTo(Score.of(score));
	}

	static Stream<Arguments> getScore() {
		return Stream.of(Arguments.of(Collections.emptyList(), 0),
				Arguments.of(Collections.singletonList(aceSpade), 11),
				Arguments.of(Arrays.asList(aceSpade, tenClub), 21),
				Arguments.of(Arrays.asList(tenClub, jackHeart, aceSpade), 21),
				Arguments.of(Arrays.asList(jackHeart, aceSpade, twoClub), 13));
	}
}