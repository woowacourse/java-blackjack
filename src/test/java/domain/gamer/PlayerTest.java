package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuit;
import domain.result.MatchResult;

public class PlayerTest {
	@ParameterizedTest
	@MethodSource("generateCards")
	@DisplayName("플레이어가 추가로 카드를 뽑을 수 있을지 테스트")
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
	@MethodSource("generateWinResult")
	@DisplayName("플레이어가 승리한 경우 테스트")
	void resultWinTest(List<Card> cards, List<Card> dealerCards, MatchResult expected) {
		Player player = new Player("pobi", "50");
		Dealer dealer = new Dealer();
		player.addCard(cards);
		dealer.addCard(dealerCards);

		assertThat(player.findMatchResult(dealer)).isEqualTo(expected);
	}

	static Stream<Arguments> generateWinResult() {
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
					new Card(CardSuit.CLOVER, CardNumber.FOUR)), MatchResult.WIN));
	}

	@ParameterizedTest
	@MethodSource("generateDrawResult")
	@DisplayName("플레이어와 딜러가 비긴 경우 테스트")
	void resultDrawTest(List<Card> cards, List<Card> dealerCards, MatchResult expected) {
		Player player = new Player("pobi", "50");
		Dealer dealer = new Dealer();
		player.addCard(cards);
		dealer.addCard(dealerCards);

		assertThat(player.findMatchResult(dealer)).isEqualTo(expected);

	}

	static Stream<Arguments> generateDrawResult() {
		return Stream.of(
			Arguments.of(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.TEN),
				new Card(CardSuit.HEART, CardNumber.KING)),
				Arrays.asList(
					new Card(CardSuit.DIAMOND, CardNumber.TEN),
					new Card(CardSuit.SPADE, CardNumber.KING)), MatchResult.DRAW),
			Arguments.of(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.ACE),
				new Card(CardSuit.HEART, CardNumber.KING)),
				Arrays.asList(
					new Card(CardSuit.SPADE, CardNumber.ACE),
					new Card(CardSuit.SPADE, CardNumber.KING)), MatchResult.DRAW));
	}

	@ParameterizedTest
	@MethodSource("generateLoseResult")
	@DisplayName("플레이어가 패배한 경우 테스트")
	void resultLoseTest(List<Card> cards, List<Card> dealerCards, MatchResult expected) {
		Player player = new Player("pobi", "50");
		Dealer dealer = new Dealer();
		player.addCard(cards);
		dealer.addCard(dealerCards);

		assertThat(player.findMatchResult(dealer)).isEqualTo(expected);
	}

	static Stream<Arguments> generateLoseResult() {
		return Stream.of(
			Arguments.of(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.EIGHT),
				new Card(CardSuit.CLOVER, CardNumber.TEN)),
				Arrays.asList(
					new Card(CardSuit.DIAMOND, CardNumber.TEN),
					new Card(CardSuit.SPADE, CardNumber.KING)), MatchResult.LOSE),
			Arguments.of(Arrays.asList(
				new Card(CardSuit.HEART, CardNumber.KING),
				new Card(CardSuit.HEART, CardNumber.JACK),
				new Card(CardSuit.CLOVER, CardNumber.ACE)),
				Arrays.asList(
					new Card(CardSuit.SPADE, CardNumber.KING),
					new Card(CardSuit.SPADE, CardNumber.ACE)), MatchResult.LOSE));
	}

	@ParameterizedTest
	@MethodSource("generateBustResult")
	@DisplayName("플레이어가 버스트인 경우 테스트")
	void resultBustTest(List<Card> cards, List<Card> dealerCards, MatchResult expected) {
		Player player = new Player("pobi", "50");
		Dealer dealer = new Dealer();
		player.addCard(cards);
		dealer.addCard(dealerCards);

		assertThat(player.findMatchResult(dealer)).isEqualTo(expected);
	}

	static Stream<Arguments> generateBustResult() {
		return Stream.of(
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
					new Card(CardSuit.DIAMOND, CardNumber.FOUR)), MatchResult.BUST));
	}

	@ParameterizedTest
	@MethodSource("generateBlackJackResult")
	@DisplayName("플레이어가 블랙잭인 경우 테스트")
	void resultBlackJackTest(List<Card> cards, List<Card> dealerCards, MatchResult expected) {
		Player player = new Player("pobi", "50");
		Dealer dealer = new Dealer();
		player.addCard(cards);
		dealer.addCard(dealerCards);

		assertThat(player.findMatchResult(dealer)).isEqualTo(expected);
	}

	static Stream<Arguments> generateBlackJackResult() {
		return Stream.of(
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
					new Card(CardSuit.SPADE, CardNumber.KING),
					new Card(CardSuit.SPADE, CardNumber.JACK),
					new Card(CardSuit.SPADE, CardNumber.ACE)), MatchResult.BLACKJACK));
	}
}