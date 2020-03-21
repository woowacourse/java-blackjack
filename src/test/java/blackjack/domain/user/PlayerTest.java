package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.exceptions.PlayerException;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
	private static Card aceSpade;
	private static Card twoClub;
	private static Card sixDiamond;
	private static Card tenClub;
	private static Card jackHeart;

	private Playable player;

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
		player = Player.of("그니", "1000");
	}

	@DisplayName("of()가 인스턴스를 반환하는지 테스트")
	@Test
	void of_ValidPlayer_IsNotNull() {
		assertThat(player).isNotNull();
	}

	@DisplayName("of()가 딜러 이름일 경우 예외를 던지는지 테스트")
	@Test
	void of_HasDealerName_ThrowPlayerException() {
		assertThatThrownBy(() -> Player.of("딜러", "1000"))
				.isInstanceOf(PlayerException.class);
	}

	@DisplayName("receiveCard()가 카드를 받는지 테스트")
	@ParameterizedTest
	@MethodSource("receiveCard_Cards_GiveTopCardPlayer")
	void receiveCard_Cards_GiveTopCardPlayer(List<Card> cards) {
		// given
		for (Card card : cards) {
			player.receiveCard(card);
		}

		// then
		assertThat(player.getHand().getHand()).isEqualTo(cards);
	}

	static Stream<Arguments> receiveCard_Cards_GiveTopCardPlayer() {
		return Stream.of(Arguments.of(Collections.singletonList(aceSpade)),
				Arguments.of(Collections.singletonList(sixDiamond)),
				Arguments.of(Arrays.asList(tenClub, jackHeart)),
				Arguments.of(Arrays.asList(tenClub, tenClub)),
				Arguments.of(Arrays.asList(aceSpade, sixDiamond, tenClub, jackHeart)));
	}

	@DisplayName("receiveCards()가 여러 장의 카드를 주는지 테스트")
	@ParameterizedTest
	@MethodSource("receiveCards_Cards_GiveCardsPlayer")
	void receiveCards_Cards_GiveCardsPlayer(List<Card> cards) {
		player.receiveCards(cards);
		assertThat(player.getHand().getHand()).isEqualTo(cards);
	}

	static Stream<List<Card>> receiveCards_Cards_GiveCardsPlayer() {
		return Stream.of(Collections.singletonList(aceSpade),
				Collections.singletonList(sixDiamond),
				Arrays.asList(tenClub, jackHeart),
				Arrays.asList(tenClub, tenClub),
				Arrays.asList(aceSpade, sixDiamond, tenClub, jackHeart));
	}

	@DisplayName("isWinner()가 player가 비교 score 값보다 클 시 true를 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("isWinner_HasScoreBiggerThanInputScore_ReturnTrue")
	void isWinner_HasScoreBiggerThanInputScore_ReturnTrue(List<Card> cards, int score) {
		player.receiveCards(cards);
		assertThat(player.isWinner(Score.of(score))).isTrue();
	}

	static Stream<Arguments> isWinner_HasScoreBiggerThanInputScore_ReturnTrue() {
		return Stream.of(Arguments.of(Collections.singletonList(tenClub), 9),
				Arguments.of(Collections.singletonList(aceSpade), 10),
				Arguments.of(Arrays.asList(aceSpade, jackHeart), 20),
				Arguments.of(Arrays.asList(jackHeart, jackHeart, aceSpade), 20),
				Arguments.of(Arrays.asList(tenClub, jackHeart), 19),
				Arguments.of(Arrays.asList(tenClub, jackHeart), 0));
	}

	@DisplayName("isWinner()가 player가 비교 score 값보다 같거나 작을 시 false를 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("isWinner_HasScoreSameOrSmallerThanInputScore_ReturnFalse")
	void isWinner_HasScoreSameOrSmallerThanInputScore_ReturnFalse(List<Card> cards, int score) {
		player.receiveCards(cards);
		assertThat(player.isWinner(Score.of(score))).isFalse();
	}

	static Stream<Arguments> isWinner_HasScoreSameOrSmallerThanInputScore_ReturnFalse() {
		return Stream.of(Arguments.of(Collections.singletonList(tenClub), 10),
				Arguments.of(Collections.singletonList(aceSpade), 11),
				Arguments.of(Arrays.asList(jackHeart, sixDiamond), 16),
				Arguments.of(Arrays.asList(jackHeart, sixDiamond), 21),
				Arguments.of(Arrays.asList(aceSpade, jackHeart), 21));
	}

	@DisplayName("computeScore()가 적절한 값을 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("computeScore_HasCards_ReturnScore")
	void computeScore_HasCards_ReturnScore(List<Card> cards, int score) {
		player.receiveCards(cards);
		assertThat(player.computeScore()).isEqualTo(Score.of(score));
	}

	static Stream<Arguments> computeScore_HasCards_ReturnScore() {
		return Stream.of(Arguments.of(Collections.singletonList(aceSpade), 11),
				Arguments.of(Arrays.asList(aceSpade, jackHeart), 21),
				Arguments.of(Arrays.asList(aceSpade, jackHeart, sixDiamond), 17),
				Arguments.of(Arrays.asList(aceSpade, jackHeart, sixDiamond, aceSpade), 18),
				Arguments.of(Collections.emptyList(), 0));
	}

	@DisplayName("isBust()가 점수가 21보다 클 시 true를 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("isBust_ScoreMoreThanTwelve_ReturnTrue")
	void isBust_ScoreMoreThanTwelve_ReturnTrue(List<Card> cards) {
		player.receiveCards(cards);
		assertThat(player.isBust()).isTrue();
	}

	static Stream<List<Card>> isBust_ScoreMoreThanTwelve_ReturnTrue() {
		return Stream.of(Arrays.asList(tenClub, tenClub, twoClub),
				Arrays.asList(jackHeart, sixDiamond, sixDiamond),
				Arrays.asList(jackHeart, jackHeart, aceSpade, aceSpade));
	}

	@DisplayName("isBust()가 점수가 21이하일 시 false를 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("isBust_ScoreSameOrLessThanTwelve_ReturnFalse")
	void isBust_ScoreSameOrLessThanTwelve_ReturnFalse(List<Card> cards) {
		player.receiveCards(cards);
		assertThat(player.isBust()).isFalse();
	}

	static Stream<List<Card>> isBust_ScoreSameOrLessThanTwelve_ReturnFalse() {
		return Stream.of(Collections.emptyList(),
				Collections.singletonList(aceSpade),
				Arrays.asList(tenClub, aceSpade),
				Arrays.asList(tenClub, jackHeart, aceSpade));
	}

	@DisplayName("canReceiveCard()가 버스트되지 않았을 시 true를 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("canReceiveCard_NotBusted_ReturnTrue")
	void canReceiveCard_NotBusted_ReturnTrue(List<Card> cards) {
		player.receiveCards(cards);
		assertThat(player.canReceiveCard()).isTrue();
	}

	static Stream<List<Card>> canReceiveCard_NotBusted_ReturnTrue() {
		return Stream.of(Arrays.asList(tenClub, aceSpade),
				Arrays.asList(jackHeart, jackHeart, aceSpade),
				Collections.emptyList(),
				Arrays.asList(tenClub, sixDiamond, aceSpade));
	}

	@DisplayName("canReceiveCard()가 버스트된 경우 false를 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("canReceiveCard_Busted_ReturnFalse")
	void canReceiveCard_Busted_ReturnFalse(List<Card> cards) {
		player.receiveCards(cards);
		assertThat(player.canReceiveCard()).isFalse();
	}

	static Stream<List<Card>> canReceiveCard_Busted_ReturnFalse() {
		return Stream.of(Arrays.asList(tenClub, sixDiamond, sixDiamond),
				Arrays.asList(tenClub, tenClub, aceSpade, aceSpade),
				Arrays.asList(tenClub, tenClub, twoClub, twoClub),
				Arrays.asList(jackHeart, jackHeart, jackHeart, jackHeart, aceSpade));
	}
}
