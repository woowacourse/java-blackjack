package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.exceptions.PlayerException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

	@Test
	void of_ValidPlayer_IsNotNull() {
		assertThat(player).isNotNull();
	}

	@Test
	void of_HasDealerName_ThrowPlayerException() {
		assertThatThrownBy(() -> Player.of("딜러", "1000"))
				.isInstanceOf(PlayerException.class);
	}

	@ParameterizedTest
	@MethodSource("giveCard_Cards_GiveTopCardPlayer")
	void giveCard_Cards_GiveTopCardPlayer(List<Card> cards) {
		// given
		for (Card card : cards) {
			player.receiveCard(card);
		}

		// then
		assertThat(player.getHand().getHand()).isEqualTo(cards);
	}

	static Stream<Arguments> giveCard_Cards_GiveTopCardPlayer() {
		return Stream.of(Arguments.of(Collections.singletonList(aceSpade)),
				Arguments.of(Collections.singletonList(sixDiamond)),
				Arguments.of(Arrays.asList(tenClub, jackHeart)),
				Arguments.of(Arrays.asList(tenClub, tenClub)),
				Arguments.of(Arrays.asList(aceSpade, sixDiamond, tenClub, jackHeart)));
	}

	@ParameterizedTest
	@MethodSource("giveCards_Cards_GiveCardsPlayer")
	void giveCards_Cards_GiveCardsPlayer(List<Card> cards) {
		player.receiveCards(cards);
		assertThat(player.getHand().getHand()).isEqualTo(cards);
	}

	static Stream<List<Card>> giveCards_Cards_GiveCardsPlayer() {
		return Stream.of(Collections.singletonList(aceSpade),
				Collections.singletonList(sixDiamond),
				Arrays.asList(tenClub, jackHeart),
				Arrays.asList(tenClub, tenClub),
				Arrays.asList(aceSpade, sixDiamond, tenClub, jackHeart));
	}

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

	@ParameterizedTest
	@MethodSource("getScore_HasCards_ReturnScore")
	void getScore_HasCards_ReturnScore(List<Card> cards, int score) {
		player.receiveCards(cards);
		assertThat(player.computeScore()).isEqualTo(Score.of(score));
	}

	static Stream<Arguments> getScore_HasCards_ReturnScore() {
		return Stream.of(Arguments.of(Collections.singletonList(aceSpade), 11),
				Arguments.of(Arrays.asList(aceSpade, jackHeart), 21),
				Arguments.of(Arrays.asList(aceSpade, jackHeart, sixDiamond), 17),
				Arguments.of(Arrays.asList(aceSpade, jackHeart, sixDiamond, aceSpade), 18),
				Arguments.of(Collections.emptyList(), 0));
	}

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

	@Test
	void getName_HasName_ReturnName() {
		assertThat(player.getName()).isEqualTo(new Name("그니"));
	}

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
