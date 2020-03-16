package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.exceptions.AbstractPlayerException;
import blackjack.domain.user.exceptions.PlayerException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
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
		player = Player.of("그니");
	}

	@Test
	void of_IsNotNull() {
		assertThat(player).isNotNull();
	}

	@Test
	void of_ThrowPlayerException() {
		assertThatThrownBy(() -> Player.of("딜러"))
				.isInstanceOf(PlayerException.class);
	}

	@ParameterizedTest
	@MethodSource("of_ThrowAbstractException")
	void of_ThrowAbstractException(String invalidName) {
		assertThatThrownBy(() -> Player.of(invalidName))
				.isInstanceOf(AbstractPlayerException.class);
	}

	static Stream<String> of_ThrowAbstractException() {
		return Stream.of(null, "", " ", "  ", "   ");
	}

	@ParameterizedTest
	@MethodSource("giveCard")
	void giveCard(List<Card> cards) {
		// given
		for (Card card : cards) {
			player.giveCard(card);
		}

		// then
		assertThat(player.getHand()).isEqualTo(cards);
	}

	static Stream<Arguments> giveCard() {
		return Stream.of(Arguments.of(Collections.singletonList(aceSpade)),
				Arguments.of(Collections.singletonList(sixDiamond)),
				Arguments.of(Arrays.asList(tenClub, jackHeart)),
				Arguments.of(Arrays.asList(tenClub, tenClub)),
				Arguments.of(Arrays.asList(aceSpade, sixDiamond, tenClub, jackHeart)));
	}

	@ParameterizedTest
	@MethodSource("giveCards")
	void giveCards(List<Card> cards) {
		player.giveCards(cards);
		assertThat(player.getHand()).isEqualTo(cards);
	}

	static Stream<List<Card>> giveCards() {
		return Stream.of(Collections.singletonList(aceSpade),
				Collections.singletonList(sixDiamond),
				Arrays.asList(tenClub, jackHeart),
				Arrays.asList(tenClub, tenClub),
				Arrays.asList(aceSpade, sixDiamond, tenClub, jackHeart));
	}

	@ParameterizedTest
	@MethodSource("isWinner_ReturnTrue")
	void isWinner_ReturnTrue(List<Card> cards, int score) {
		player.giveCards(cards);
		assertThat(player.isWinner(Score.of(score))).isTrue();
	}

	static Stream<Arguments> isWinner_ReturnTrue() {
		return Stream.of(Arguments.of(Collections.singletonList(tenClub), 9),
				Arguments.of(Collections.singletonList(aceSpade), 10),
				Arguments.of(Arrays.asList(aceSpade, jackHeart), 20),
				Arguments.of(Arrays.asList(jackHeart, jackHeart, aceSpade), 20),
				Arguments.of(Arrays.asList(tenClub, jackHeart), 19),
				Arguments.of(Arrays.asList(tenClub, jackHeart), 0));
	}

	@ParameterizedTest
	@MethodSource("isWinner_ReturnFalse")
	void isWinner_ReturnFalse(List<Card> cards, int score) {
		player.giveCards(cards);
		assertThat(player.isWinner(Score.of(score))).isFalse();
	}

	static Stream<Arguments> isWinner_ReturnFalse() {
		return Stream.of(Arguments.of(Collections.singletonList(tenClub), 10),
				Arguments.of(Collections.singletonList(aceSpade), 11),
				Arguments.of(Arrays.asList(jackHeart, sixDiamond), 16),
				Arguments.of(Arrays.asList(jackHeart, sixDiamond), 21),
				Arguments.of(Arrays.asList(aceSpade, jackHeart), 21));
	}

	@ParameterizedTest
	@MethodSource("getScore")
	void getScore(List<Card> cards, int score) {
		player.giveCards(cards);
		assertThat(player.getScore()).isEqualTo(Score.of(score));
	}

	static Stream<Arguments> getScore() {
		return Stream.of(Arguments.of(Collections.singletonList(aceSpade), 11),
				Arguments.of(Arrays.asList(aceSpade, jackHeart), 21),
				Arguments.of(Arrays.asList(aceSpade, jackHeart, sixDiamond), 17),
				Arguments.of(Arrays.asList(aceSpade, jackHeart, sixDiamond, aceSpade), 18),
				Arguments.of(Collections.emptyList(), 0));
	}

	@ParameterizedTest
	@MethodSource("isBust_ReturnTrue")
	void isBust_ReturnTrue(List<Card> cards) {
		player.giveCards(cards);
		assertThat(player.isBust()).isTrue();
	}

	static Stream<List<Card>> isBust_ReturnTrue() {
		return Stream.of(Arrays.asList(tenClub, tenClub, twoClub),
				Arrays.asList(jackHeart, sixDiamond, sixDiamond),
				Arrays.asList(jackHeart, jackHeart, aceSpade, aceSpade));
	}

	@ParameterizedTest
	@MethodSource("isBust_ReturnFalse")
	void isBust_ReturnFalse(List<Card> cards) {
		player.giveCards(cards);
		assertThat(player.isBust()).isFalse();
	}

	static Stream<List<Card>> isBust_ReturnFalse() {
		return Stream.of(Collections.emptyList(),
				Collections.singletonList(aceSpade),
				Arrays.asList(tenClub, aceSpade),
				Arrays.asList(tenClub, jackHeart, aceSpade));
	}

	@ParameterizedTest
	@MethodSource("getCards")
	void getCards(List<Card> cards) {
		player.giveCards(cards);
		assertThat(player.getHand()).isEqualTo(cards);
	}

	static Stream<List<Card>> getCards() {
		return Stream.of(Collections.emptyList(),
				Collections.singletonList(aceSpade),
				Arrays.asList(aceSpade, tenClub),
				Arrays.asList(jackHeart, tenClub, jackHeart));
	}

	@ParameterizedTest
	@MethodSource("countCards")
	void countCards(List<Card> cards, int count) {
		player.giveCards(cards);
		assertThat(player.countCards()).isEqualTo(count);
	}

	static Stream<Arguments> countCards() {
		return Stream.of(Arguments.of(Collections.emptyList(), 0),
				Arguments.of(Collections.singletonList(aceSpade), 1),
				Arguments.of(Arrays.asList(twoClub, twoClub), 2),
				Arguments.of(Arrays.asList(aceSpade, twoClub, sixDiamond, tenClub, jackHeart), 5));
	}

	@Test
	void getName() {
		assertThat(player.getName()).isEqualTo("그니");
	}

	@ParameterizedTest
	@MethodSource("canReceiveCard_ReturnTrue")
	void canReceiveCard_ReturnTrue(List<Card> cards) {
		player.giveCards(cards);
		assertThat(player.canReceiveCard()).isTrue();
	}

	static Stream<List<Card>> canReceiveCard_ReturnTrue() {
		return Stream.of(Arrays.asList(tenClub, aceSpade),
				Arrays.asList(jackHeart, jackHeart, aceSpade),
				Collections.emptyList(),
				Arrays.asList(tenClub, sixDiamond, aceSpade));
	}

	@ParameterizedTest
	@MethodSource("canReceiveCard_ReturnFalse")
	void canReceiveCard_ReturnFalse(List<Card> cards) {
		player.giveCards(cards);
		assertThat(player.canReceiveCard()).isFalse();
	}

	static Stream<List<Card>> canReceiveCard_ReturnFalse() {
		return Stream.of(Arrays.asList(tenClub, sixDiamond, sixDiamond),
				Arrays.asList(tenClub, tenClub, aceSpade, aceSpade),
				Arrays.asList(tenClub, tenClub, twoClub, twoClub),
				Arrays.asList(jackHeart, jackHeart, jackHeart, jackHeart, aceSpade));
	}
}
