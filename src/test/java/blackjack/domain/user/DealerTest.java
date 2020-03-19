package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
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

class DealerTest {
	private static Card aceSpade;
	private static Card twoClub;
	private static Card sixDiamond;
	private static Card tenClub;
	private static Card jackHeart;

	private Playable dealer;

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
		dealer = Dealer.dealer();
	}

	@Test
	void dealer_IsNotNull() {
		assertThat(dealer).isNotNull();
	}

	@ParameterizedTest
	@MethodSource("giveCard_Cards_GiveCardsHandOfDealer")
	void giveCard_Cards_GiveCardsHandOfDealer(List<Card> cards) {
		// given
		for (Card card : cards) {
			dealer.receiveCard(card);
		}

		// then
		assertThat(dealer.getHand()).isEqualTo(cards);
	}

	static Stream<Arguments> giveCard_Cards_GiveCardsHandOfDealer() {
		return Stream.of(Arguments.of(Collections.singletonList(aceSpade)),
				Arguments.of(Collections.singletonList(sixDiamond)),
				Arguments.of(Arrays.asList(tenClub, jackHeart)),
				Arguments.of(Arrays.asList(tenClub, tenClub)),
				Arguments.of(Arrays.asList(aceSpade, sixDiamond, tenClub, jackHeart)));
	}

	@ParameterizedTest
	@MethodSource("giveCards_Cards_GiveCardsHandOfDealer")
	void giveCards_Cards_GiveCardsHandOfDealer(List<Card> cards) {
		dealer.receiveCards(cards);
		assertThat(dealer.getHand()).isEqualTo(cards);
	}

	static Stream<List<Card>> giveCards_Cards_GiveCardsHandOfDealer() {
		return Stream.of(Collections.singletonList(aceSpade),
				Collections.singletonList(sixDiamond),
				Arrays.asList(tenClub, jackHeart),
				Arrays.asList(tenClub, tenClub),
				Arrays.asList(aceSpade, sixDiamond, tenClub, jackHeart));
	}

	@ParameterizedTest
	@MethodSource("getStartHand_Cards_IsEqualToFirstCardList")
	void getStartHand_Cards_IsEqualToFirstCardList(List<Card> cards, List<Card> firstCardList) {
		dealer.receiveCards(cards);
		assertThat(dealer.getStartHand()).isEqualTo(firstCardList);
	}

	static Stream<Arguments> getStartHand_Cards_IsEqualToFirstCardList() {
		return Stream.of(
				Arguments.of(Collections.singletonList(aceSpade),
						Collections.singletonList(aceSpade)),
				Arguments.of(Collections.singletonList(sixDiamond),
						Collections.singletonList(sixDiamond)),
				Arguments.of(Arrays.asList(tenClub, tenClub),
						Collections.singletonList(tenClub)),
				Arguments.of(Arrays.asList(aceSpade, sixDiamond, tenClub, jackHeart),
						Collections.singletonList(aceSpade)));
	}

	@ParameterizedTest
	@MethodSource("isWinner_NotBusted_ReturnTrue")
	void isWinner_NotBusted_ReturnTrue(List<Card> cards) {
		dealer.receiveCards(cards);
		assertThat(dealer.isWinner(Score.of(21))).isTrue();
	}

	static Stream<List<Card>> isWinner_NotBusted_ReturnTrue() {
		return Stream.of(Collections.singletonList(aceSpade),
				Collections.singletonList(sixDiamond),
				Arrays.asList(tenClub, jackHeart),
				Arrays.asList(tenClub, tenClub),
				Arrays.asList(aceSpade, tenClub, tenClub));
	}

	@ParameterizedTest
	@MethodSource("isWinner_Busted_ReturnFalse")
	void isWinner_Busted_ReturnFalse(List<Card> cards) {
		dealer.receiveCards(cards);
		assertThat(dealer.isWinner(Score.of(0))).isFalse();
	}

	static Stream<List<Card>> isWinner_Busted_ReturnFalse() {
		return Stream.of(Arrays.asList(aceSpade, tenClub, tenClub, aceSpade),
				Arrays.asList(twoClub, jackHeart, jackHeart),
				Arrays.asList(tenClub, sixDiamond, sixDiamond));
	}

	@ParameterizedTest
	@MethodSource("getScore_ReturnItsScore")
	void getScore_ReturnItsScore(List<Card> cards, int score) {
		dealer.receiveCards(cards);
		assertThat(dealer.computeScore()).isEqualTo(Score.of(score));
	}

	static Stream<Arguments> getScore_ReturnItsScore() {
		return Stream.of(Arguments.of(Collections.singletonList(aceSpade), 11),
				Arguments.of(Arrays.asList(aceSpade, jackHeart), 21),
				Arguments.of(Arrays.asList(aceSpade, jackHeart, sixDiamond), 17),
				Arguments.of(Arrays.asList(aceSpade, jackHeart, sixDiamond, aceSpade), 18),
				Arguments.of(Collections.emptyList(), 0));
	}

	@ParameterizedTest
	@MethodSource("isBust_MoreThanTwentyOne_ReturnTrue")
	void isBust_MoreThanTwentyOne_ReturnTrue(List<Card> cards) {
		dealer.receiveCards(cards);
		assertThat(dealer.isBust()).isTrue();
	}

	static Stream<List<Card>> isBust_MoreThanTwentyOne_ReturnTrue() {
		return Stream.of(Arrays.asList(tenClub, tenClub, twoClub),
				Arrays.asList(jackHeart, sixDiamond, sixDiamond),
				Arrays.asList(jackHeart, jackHeart, aceSpade, aceSpade));
	}

	@ParameterizedTest
	@MethodSource("isBust_SameOrLessThanTwelve_ReturnFalse")
	void isBust_SameOrLessThanTwelve_ReturnFalse(List<Card> cards) {
		dealer.receiveCards(cards);
		assertThat(dealer.isBust()).isFalse();
	}

	static Stream<List<Card>> isBust_SameOrLessThanTwelve_ReturnFalse() {
		return Stream.of(Collections.emptyList(),
				Collections.singletonList(aceSpade),
				Arrays.asList(tenClub, aceSpade),
				Arrays.asList(tenClub, jackHeart, aceSpade));
	}

	@ParameterizedTest
	@MethodSource("getCards_ReturnHand")
	void getCards_ReturnHand(List<Card> cards) {
		dealer.receiveCards(cards);
		assertThat(dealer.getHand()).isEqualTo(cards);
	}

	static Stream<List<Card>> getCards_ReturnHand() {
		return Stream.of(Collections.emptyList(),
				Collections.singletonList(aceSpade),
				Arrays.asList(aceSpade, tenClub),
				Arrays.asList(jackHeart, tenClub, jackHeart));
	}

	@ParameterizedTest
	@MethodSource("countCards_ReturnCount")
	void countCards_ReturnCount(List<Card> cards, int count) {
		dealer.receiveCards(cards);
		assertThat(dealer.countCards()).isEqualTo(count);
	}

	static Stream<Arguments> countCards_ReturnCount() {
		return Stream.of(Arguments.of(Collections.emptyList(), 0),
				Arguments.of(Collections.singletonList(aceSpade), 1),
				Arguments.of(Arrays.asList(twoClub, twoClub), 2),
				Arguments.of(Arrays.asList(aceSpade, twoClub, sixDiamond, tenClub, jackHeart), 5));
	}

	@Test
	void getName_Dealer_ReturnDealerName() {
		assertThat(dealer.getName()).isEqualTo(new Name("딜러"));
	}

	@ParameterizedTest
	@MethodSource("canReceiveCard_LessThanSeventeen_ReturnTrue")
	void canReceiveCard_LessThanSeventeen_ReturnTrue(List<Card> cards) {
		dealer.receiveCards(cards);
		assertThat(dealer.canReceiveCard()).isTrue();
	}

	static Stream<List<Card>> canReceiveCard_LessThanSeventeen_ReturnTrue() {
		return Stream.of(Arrays.asList(tenClub, sixDiamond),
				Collections.emptyList(),
				Arrays.asList(tenClub, twoClub, twoClub, aceSpade, aceSpade));
	}

	@ParameterizedTest
	@MethodSource("canReceiveCard_MoreThanSixteen_ReturnFalse")
	void canReceiveCard_MoreThanSixteen_ReturnFalse(List<Card> cards) {
		dealer.receiveCards(cards);
		assertThat(dealer.canReceiveCard()).isFalse();
	}

	static Stream<List<Card>> canReceiveCard_MoreThanSixteen_ReturnFalse() {
		return Stream.of(Arrays.asList(tenClub, sixDiamond, aceSpade),
				Arrays.asList(tenClub, aceSpade),
				Arrays.asList(jackHeart, jackHeart));
	}
}