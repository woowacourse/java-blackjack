package domain.user;

import static domain.card.Symbol.*;
import static domain.card.Type.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.score.Score;

public class PlayerTest {
	@DisplayName("플레이어의 카드가 2장이면서, 총 점수가 21점이면, 블랙잭이다.")
	@Test
	void isBlackjackTrueTest() {
		Player player = Player.fromNameAndMoneyAndCards("동글", 1_000, Card.of(CLOVER, ACE), Card.of(CLOVER, JACK));
		assertThat(player.isBlackjack()).isTrue();
	}

	@DisplayName("플레이어의 카드가 2장이 아니거나 총 점수가 21점이 아니면, 블랙잭이 아니다.")
	@ParameterizedTest
	@MethodSource("player_not_black_jack_card_set")
	void isBlackjackFalseTest(Card[] cards) {
		Player player = Player.fromNameAndMoneyAndCards("동글", 1_000, cards);
		assertThat(player.isBlackjack()).isFalse();
	}

	private static Stream<Arguments> player_not_black_jack_card_set() {
		return Stream.of(
			Arguments.of((Object)new Card[] {Card.of(CLOVER, ACE), Card.of(CLOVER, FIVE), Card.of(HEART, FIVE)}),
			Arguments.of((Object)new Card[] {Card.of(CLOVER, ACE), Card.of(CLOVER, FIVE)}),
			Arguments.of((Object)new Card[] {Card.of(CLOVER, ACE), Card.of(CLOVER, FIVE), Card.of(CLOVER, THREE)})
		);
	}

	@DisplayName("첫번째 드로우 카드 리스트를 가져올때, 플레이어의 카드 중 첫번째 두장을 가져온다.")
	@Test
	void getInitialCardTest() {
		Player player = Player.fromNameAndMoneyAndCards("name", 1_000, Card.of(HEART, EIGHT), Card.of(CLOVER, NINE),
			Card.of(DIAMOND, TWO));
		assertThat(player.getFirstOpenCards()).containsExactly(Card.of(HEART, EIGHT), Card.of(CLOVER, NINE));
	}

	@DisplayName("플레이어의 카드 점수 합계가 21점이 넘어가는 경우 버스트다.")
	@Test
	void isBustTrueTest() {
		Player player = Player.fromNameAndMoneyAndCards("test", 1_000, Card.of(HEART, EIGHT), Card.of(CLOVER, NINE),
			Card.of(SPADE, JACK));
		assertThat(player.isBust()).isTrue();
	}

	@DisplayName("플레이어의 카드 점수 합계가 21점이 넘지 않는 경우 버스트가 아니다.")
	@Test
	void isBustFalseTest() {
		Player player = Player.fromNameAndMoneyAndCards("test", 1_000, Card.of(HEART, EIGHT), Card.of(SPADE, JACK));
		assertThat(player.isBust()).isFalse();
	}

	@DisplayName("블랙잭이 아니고, 버스트가 아닌 경우 추가 드로우 할 수 있다.")
	@Test
	void isDrawableTest() {
		Player player = Player.fromNameAndMoneyAndCards("test", 1_000, Card.of(HEART, EIGHT), Card.of(SPADE, JACK));
		assertThat(player.isDrawable()).isTrue();
	}

	@DisplayName("가지고 있는 카드의 점수를 합산한 결과를 반환한다.")
	@ParameterizedTest
	@MethodSource("calculateScoreCardSet")
	void calculate_score_test(Score expected, Card[] cards) {
		Player dongle = Player.fromNameAndMoneyAndCards("dongle", 1_000, cards);
		assertThat(dongle.calculateScore()).isEqualTo(expected);
	}

	private static Stream<Arguments> calculateScoreCardSet() {
		return Stream.of(
			Arguments.of(Score.ofValue(19), new Card[] {Card.of(HEART, ACE), Card.of(HEART, EIGHT)}),
			Arguments.of(Score.ofValue(21), new Card[] {Card.of(HEART, ACE), Card.of(HEART, JACK)}),
			Arguments.of(Score.ofValue(21),
				new Card[] {Card.of(HEART, ACE), Card.of(HEART, FIVE), Card.of(DIAMOND, FIVE)}),
			Arguments.of(Score.ofValue(14),
				new Card[] {Card.of(HEART, ACE), Card.of(DIAMOND, ACE), Card.of(HEART, QUEEN),
					Card.of(SPADE, ACE), Card.of(CLOVER, ACE)}),
			Arguments.of(Score.ofValue(24),
				new Card[] {Card.of(HEART, TEN), Card.of(DIAMOND, FOUR), Card.of(CLOVER, TEN)})
		);
	}

	@DisplayName("내 점수가 다른 카드의 점수보다 큰 경우, 참을 반환한다.")
	@Test
	void comparing_higher_score_test() {
		Player dongle = Player.fromNameAndMoneyAndCards("dongle", 1_000, Card.of(HEART, ACE), Card.of(HEART, EIGHT));
		Player other = Player.fromNameAndMoneyAndCards("other", 1_000, Card.of(CLOVER, ACE), Card.of(CLOVER, SEVEN));
		assertThat(dongle.hasHigherScoreThan(other)).isEqualTo(true);
	}

	@DisplayName("내 점수가 다른 카드의 점수와 같 경우, 참을 반환한다.")
	@Test
	void comparing_same_score_test() {
		Player dongle = Player.fromNameAndMoneyAndCards("dongle", 1_000, Card.of(HEART, ACE), Card.of(HEART, EIGHT));
		Player other = Player.fromNameAndMoneyAndCards("other", 1_000, Card.of(CLOVER, ACE), Card.of(CLOVER, EIGHT));
		assertThat(dongle.hasSameScoreWith(other)).isEqualTo(true);
	}

	@DisplayName("내 점수가 다른 카드의 점수보다 작은 경우, 참을 반환한다.")
	@Test
	void comparing_lower_score_test() {
		Player dongle = Player.fromNameAndMoneyAndCards("dongle", 1_000, Card.of(HEART, ACE), Card.of(HEART, EIGHT));
		Player other = Player.fromNameAndMoneyAndCards("other", 1_000, Card.of(CLOVER, ACE), Card.of(CLOVER, NINE));
		assertThat(dongle.hasLowerScoreThan(other)).isEqualTo(true);
	}
}
