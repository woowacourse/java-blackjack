package domain.gamer;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuit;
import exception.NameFormatException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
	@Test
	@DisplayName("잘못된 이름 입력시 예외처리")
	void isValidNameTest() {
		assertThatThrownBy(() -> new Player("po/bi", "50")).isInstanceOf(NameFormatException.class);
	}

	@ParameterizedTest
	@MethodSource("generateCards")
	public void isDrawableTest(List<Card> cards, boolean expected) {
		Player player = new Player("pobi", "50");
		player.addCard(cards);
		assertThat(player.isDrawable()).isEqualTo(expected);
	}

	static Stream<Arguments> generateCards() {
		return Stream.of(
			Arguments.of(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.SIX),
				new Card(CardSuit.CLOVER, CardNumber.TEN)), true),
			Arguments.of(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.KING),
				new Card(CardSuit.CLOVER, CardNumber.TEN),
				new Card(CardSuit.CLOVER, CardNumber.THREE)), false));
	}

	@ParameterizedTest
	@DisplayName("플레이어점수와 딜러점수 비교해서 경기결과내는 테스트")
	@MethodSource("generateCardsAndDealerScore")
	void findMatchResultTest(List<Card> cards, List<Card> dealerCards, MatchResult expected) {
		Player player = new Player("pobi", "50");
		Dealer dealer = new Dealer();
		player.addCard(cards);
		dealer.addCard(dealerCards);

		assertThat(player.findMatchResult(dealer)).isEqualTo(expected);
	}

	static Stream<Arguments> generateCardsAndDealerScore() {
		return Stream.of(
			Arguments.of(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.EIGHT),
				new Card(CardSuit.CLOVER, CardNumber.TEN)),
				Arrays.asList(
					new Card(CardSuit.CLOVER, CardNumber.SEVEN),
					new Card(CardSuit.CLOVER, CardNumber.TEN)), MatchResult.WIN),
			Arguments.of(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.EIGHT),
				new Card(CardSuit.CLOVER, CardNumber.TEN)),
				Arrays.asList(
					new Card(CardSuit.CLOVER, CardNumber.EIGHT),
					new Card(CardSuit.CLOVER, CardNumber.TEN),
					new Card(CardSuit.CLOVER, CardNumber.FOUR)), MatchResult.WIN),
			Arguments.of(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.TEN),
				new Card(CardSuit.HEART, CardNumber.KING)),
				Arrays.asList(
					new Card(CardSuit.DIAMOND, CardNumber.TEN),
					new Card(CardSuit.SPADE, CardNumber.KING)), MatchResult.DRAW),
			Arguments.of(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.EIGHT),
				new Card(CardSuit.CLOVER, CardNumber.TEN)),
				Arrays.asList(
					new Card(CardSuit.DIAMOND, CardNumber.TEN),
					new Card(CardSuit.SPADE, CardNumber.KING)), MatchResult.LOSE),
			Arguments.of(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.EIGHT),
				new Card(CardSuit.CLOVER, CardNumber.TEN),
				new Card(CardSuit.HEART, CardNumber.KING)),
				Arrays.asList(
					new Card(CardSuit.DIAMOND, CardNumber.TEN),
					new Card(CardSuit.SPADE, CardNumber.KING)), MatchResult.BUST),
			Arguments.of(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.EIGHT),
				new Card(CardSuit.CLOVER, CardNumber.TEN),
				new Card(CardSuit.CLOVER, CardNumber.FOUR)),
				Arrays.asList(
					new Card(CardSuit.DIAMOND, CardNumber.EIGHT),
					new Card(CardSuit.DIAMOND, CardNumber.TEN),
					new Card(CardSuit.DIAMOND, CardNumber.FOUR)), MatchResult.BUST),
			Arguments.of(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.ACE),
				new Card(CardSuit.HEART, CardNumber.KING)),
				Arrays.asList(
					new Card(CardSuit.DIAMOND, CardNumber.TEN),
					new Card(CardSuit.SPADE, CardNumber.KING)), MatchResult.BLACKJACK),
			Arguments.of(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.ACE),
				new Card(CardSuit.HEART, CardNumber.KING)),
				Arrays.asList(
					new Card(CardSuit.SPADE, CardNumber.ACE),
					new Card(CardSuit.SPADE, CardNumber.KING)), MatchResult.DRAW),
			Arguments.of(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.ACE),
				new Card(CardSuit.HEART, CardNumber.KING)),
				Arrays.asList(
					new Card(CardSuit.SPADE, CardNumber.KING),
					new Card(CardSuit.SPADE, CardNumber.JACK),
					new Card(CardSuit.SPADE, CardNumber.ACE)), MatchResult.BLACKJACK),
			Arguments.of(Arrays.asList(
				new Card(CardSuit.HEART, CardNumber.KING),
				new Card(CardSuit.HEART, CardNumber.JACK),
				new Card(CardSuit.CLOVER, CardNumber.ACE)),
				Arrays.asList(
					new Card(CardSuit.SPADE, CardNumber.KING),
					new Card(CardSuit.SPADE, CardNumber.ACE)), MatchResult.LOSE));
	}
}