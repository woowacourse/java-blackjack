package blackjack.domain.user;

import blackjack.domain.card.Card;
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

import static blackjack.domain.testAssistant.TestAssistant.*;
import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

	@DisplayName("receiveCard()가 카드를 받는지 테스트")
	@ParameterizedTest
	@MethodSource("receiveCard_Cards_GiveCardsHandOfDealer")
	void receiveCard_Cards_GiveCardsHandOfDealer(Dealer dealer, Card card, Dealer expect) {
		dealer.receiveCard(card);

		assertThat(dealer).isEqualTo(expect);
	}

	static Stream<Arguments> receiveCard_Cards_GiveCardsHandOfDealer() {
		return Stream.of(
				Arguments.of(createDealer(), createCard("ACE,SPADE"), createDealer("ACE,SPADE")),
				Arguments.of(createDealer("JACK,SPADE"),
						createCard("ACE,SPADE"),
						createDealer("JACK,SPADE", "ACE,SPADE"))
		);
	}

	@DisplayName("receiveCards()가 여러 장의 카드를 주는지 테스트")
	@ParameterizedTest
	@MethodSource("receiveCards_Cards_GiveCardsHandOfDealer")
	void receiveCards_Cards_GiveCardsHandOfDealer(Dealer dealer, List<Card> cards, Dealer expect) {
		dealer.receiveCards(cards);

		assertThat(dealer).isEqualTo(expect);
	}

	static Stream<Arguments> receiveCards_Cards_GiveCardsHandOfDealer() {
		return Stream.of(
				Arguments.of(createDealer(), createCards(), createDealer()),
				Arguments.of(createDealer("ACE,SPADE"),
						createCards("TWO,CLUB", "THREE,HEART"),
						createDealer("ACE,SPADE", "TWO,CLUB", "THREE,HEART"))
		);
	}

	@DisplayName("getStartHand()가 한 장의 카드 리스트만 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("getStartHand_Cards_IsEqualToFirstCardList")
	void getStartHand_Cards_IsEqualToFirstCardList(Dealer dealer, List<Card> firstCardList) {
		assertThat(dealer.getStartHand()).isEqualTo(firstCardList);
	}

	static Stream<Arguments> getStartHand_Cards_IsEqualToFirstCardList() {
		return Stream.of(
				Arguments.of(createDealer("ACE,SPADE"),
						createCards("ACE,SPADE")),
				Arguments.of(createDealer("SIX,CLUB", "FIVE,HEART", "FOUR,DIAMOND"),
						createCards("SIX,CLUB"))
		);
	}

	@DisplayName("canReceiveCard()가 17미만일 경우 true를 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("canReceiveCard_LessThanSeventeen_ReturnTrue")
	void canReceiveCard_LessThanSeventeen_ReturnTrue(Dealer dealer) {
		assertThat(dealer.canReceiveCard()).isTrue();
	}

	static Stream<Dealer> canReceiveCard_LessThanSeventeen_ReturnTrue() {
		return Stream.of(
				createDealer("ACE,SPADE"),
				createDealer("TWO,CLUB"),
				createDealer("TEN,CLUB", "SIX,HEART"),
				createDealer("ACE,SPADE", "FIVE,DIAMOND")
		);
	}

	@DisplayName("canReceiveCard()가 17이상일 경우 false를 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("canReceiveCard_MoreThanSixteen_ReturnFalse")
	void canReceiveCard_MoreThanSixteen_ReturnFalse(Dealer dealer) {
		assertThat(dealer.canReceiveCard()).isFalse();
	}

	static Stream<Dealer> canReceiveCard_MoreThanSixteen_ReturnFalse() {
		return Stream.of(
				createDealer("TEN,SPADE", "SEVEN,CLUB"),
				createDealer("TEN,SPADE", "SIX,HEART", "ACE,CLUB"),
				createDealer("ACE,HEART", "KING,SPADE")
		);
	}
}