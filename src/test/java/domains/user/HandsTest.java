package domains.user;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domains.card.Card;
import domains.card.Symbol;
import domains.card.Type;

class HandsTest {
	@DisplayName("카드 점수를 정상적으로 계산하는지 확인")
	@ParameterizedTest
	@MethodSource("generateData")
	void score_GivenHands_SumScore(int score, List<Card> cards) {
		Hands hands = new Hands(cards);
		assertThat(hands.score()).isEqualTo(score);
	}

	static Stream<Arguments> generateData() {
		Card ace = new Card(Symbol.ACE, Type.CLUB);
		Card ten = new Card(Symbol.TEN, Type.SPADE);
		Card king = new Card(Symbol.KING, Type.HEART);
		Card four = new Card(Symbol.FOUR, Type.DIAMOND);

		return Stream.of(
			Arguments.of(21, new ArrayList<>(Arrays.asList(ace, king))),
			Arguments.of(15, new ArrayList<>(Arrays.asList(ace, four))),
			Arguments.of(15, new ArrayList<>(Arrays.asList(ace, four, ten))),
			Arguments.of(21, new ArrayList<>(Arrays.asList(ace, ten, king)))
		);
	}

	@DisplayName("카드를 추가로 받았을 때, 버스트인지 확인")
	@ParameterizedTest
	@MethodSource("burstData")
	void isBurst_ScoreOver21_ReturnTrue(List<Card> cards) {
		Hands hands = new Hands(cards);
		assertThat(hands.isBurst()).isTrue();
	}

	static Stream<Arguments> burstData() {
		Card six = new Card(Symbol.SIX, Type.CLUB);
		Card seven = new Card(Symbol.SEVEN, Type.HEART);
		Card nine = new Card(Symbol.NINE, Type.HEART);
		Card ten = new Card(Symbol.TEN, Type.SPADE);

		return Stream.of(
			Arguments.of(new ArrayList<>(Arrays.asList(six, seven, nine))),
			Arguments.of(new ArrayList<>(Arrays.asList(seven, nine, ten))),
			Arguments.of(new ArrayList<>(Arrays.asList(six, nine, ten)))
		);
	}

	@DisplayName("블랙잭일 경우 true 반환")
	@ParameterizedTest
	@MethodSource("blackjackData")
	void isBlackJack_BlackJack_ReturnTrue(List<Card> cards) {
		Hands hands = new Hands(cards);
		assertTrue(hands.isBlackJack());
	}

	static Stream<Arguments> blackjackData() {
		Card ace = new Card(Symbol.ACE, Type.CLUB);
		Card ten = new Card(Symbol.TEN, Type.HEART);
		Card jack = new Card(Symbol.JACK, Type.HEART);
		Card queen = new Card(Symbol.QUEEN, Type.SPADE);
		Card king = new Card(Symbol.KING, Type.SPADE);

		return Stream.of(
			Arguments.of(new ArrayList<>(Arrays.asList(ace, ten))),
			Arguments.of(new ArrayList<>(Arrays.asList(ace, jack))),
			Arguments.of(new ArrayList<>(Arrays.asList(ace, queen))),
			Arguments.of(new ArrayList<>(Arrays.asList(ace, king)))
		);
	}
}
