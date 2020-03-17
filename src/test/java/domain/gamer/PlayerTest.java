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
	@MethodSource("generateDealerScore")
	@DisplayName("딜러의 점수에 따라서 플레이어가 승리하는지 패패하는지에 대한 테스트")
	void winOrLoseTest(int dealerScore, MatchResult expected) {
		Player player = new Player("pobi", "50");
		player.addCard(Arrays.asList(
			new Card(CardSuit.CLOVER, CardNumber.EIGHT),
			new Card(CardSuit.CLOVER, CardNumber.TEN)));
		assertThat(player.findMatchResult(dealerScore)).isEqualTo(expected);
	}

	static Stream<Arguments> generateDealerScore() {
		return Stream.of(
			Arguments.of(20, MatchResult.LOSE),
			Arguments.of(17, MatchResult.WIN),
			Arguments.of(22, MatchResult.WIN));
	}

	@Test
	@DisplayName("플레이어가 버스트가 되었을 경우 패패 테스트")
	void isPlayerBustTest() {
		Player player = new Player("pobi", "50");
		player.addCard(Arrays.asList(
			new Card(CardSuit.CLOVER, CardNumber.EIGHT),
			new Card(CardSuit.CLOVER, CardNumber.TEN),
			new Card(CardSuit.HEART, CardNumber.KING)));
		assertThat(player.findMatchResult(17)).isEqualTo(MatchResult.LOSE);
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
}