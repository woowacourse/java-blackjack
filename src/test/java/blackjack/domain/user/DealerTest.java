package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
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

	@DisplayName("dealer()가 인스턴스를 반환하는지 테스트")
	@Test
	void dealer_IsNotNull() {
		assertThat(dealer).isNotNull();
	}

	@DisplayName("receiveCard()가 카드를 받는지 테스트")
	@ParameterizedTest
	@MethodSource("receiveCard_Cards_GiveCardsHandOfDealer")
	void receiveCard_Cards_GiveCardsHandOfDealer(List<Card> cards) {
		// given
		for (Card card : cards) {
			dealer.receiveCard(card);
		}

		// then
		assertThat(dealer.getHand().getHand()).isEqualTo(cards);
	}

	static Stream<Arguments> receiveCard_Cards_GiveCardsHandOfDealer() {
		return Stream.of(Arguments.of(Collections.singletonList(aceSpade)),
				Arguments.of(Collections.singletonList(sixDiamond)),
				Arguments.of(Arrays.asList(tenClub, jackHeart)),
				Arguments.of(Arrays.asList(tenClub, tenClub)),
				Arguments.of(Arrays.asList(aceSpade, sixDiamond, tenClub, jackHeart)));
	}

	@DisplayName("receiveCards()가 여러 장의 카드를 주는지 테스트")
	@ParameterizedTest
	@MethodSource("receiveCards_Cards_GiveCardsHandOfDealer")
	void receiveCards_Cards_GiveCardsHandOfDealer(List<Card> cards) {
		dealer.receiveCards(cards);
		assertThat(dealer.getHand().getHand()).isEqualTo(cards);
	}

	static Stream<List<Card>> receiveCards_Cards_GiveCardsHandOfDealer() {
		return Stream.of(Collections.singletonList(aceSpade),
				Collections.singletonList(sixDiamond),
				Arrays.asList(tenClub, jackHeart),
				Arrays.asList(tenClub, tenClub),
				Arrays.asList(aceSpade, sixDiamond, tenClub, jackHeart));
	}

	@DisplayName("getStartHand()가 한 장의 카드 리스트만 반환하는지 테스트")
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

	@DisplayName("computeScore()가 점수를 적절히 계산하는지 테스트")
	@ParameterizedTest
	@MethodSource("computeScore_ReturnItsScore")
	void computeScore_ReturnItsScore(List<Card> cards, int score) {
		dealer.receiveCards(cards);
		assertThat(dealer.computeScore()).isEqualTo(Score.of(score));
	}

	static Stream<Arguments> computeScore_ReturnItsScore() {
		return Stream.of(Arguments.of(Collections.singletonList(aceSpade), 11),
				Arguments.of(Arrays.asList(aceSpade, jackHeart), 21),
				Arguments.of(Arrays.asList(aceSpade, jackHeart, sixDiamond), 17),
				Arguments.of(Arrays.asList(aceSpade, jackHeart, sixDiamond, aceSpade), 18),
				Arguments.of(Collections.emptyList(), 0));
	}

	@DisplayName("getName()이 딜러의 이름을 반환하는지 테스트")
	@Test
	void getName_Dealer_ReturnDealerName() {
		assertThat(dealer.getName()).isEqualTo(new Name("딜러"));
	}

	@DisplayName("canReceiveCard()가 17미만일 경우 true를 반환하는지 테스트")
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

	@DisplayName("canReceiveCard()가 17이상일 경우 false를 반환하는지 테스트")
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