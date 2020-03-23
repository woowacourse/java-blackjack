package blackjack.card.domain;

import static blackjack.card.domain.component.Symbol.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.card.domain.component.CardNumber;

class CardBundleTest {

	private static Stream<Arguments> cardsProvider() {
		return Stream.of(
			Arguments.of(
				Arrays.asList(Card.of(HEART, CardNumber.ACE)), true
			),
			Arguments.of(
				Arrays.asList(Card.of(HEART, CardNumber.FIVE), Card.of(HEART, CardNumber.SIX)), false
			)
		);
	}

	@DisplayName("카드의 점수 계산 로직, ACE가 있는 경우에 21을 넘지 않으면 10을 더한 점수를 반환한다.")
	@ParameterizedTest
	@CsvSource(value = {"ACE, 20", "TWO,21"})
	void calculateScore(CardNumber number, int expect) {
		//given
		CardBundle cardBundle = new CardBundle();
		cardBundle.addCard(Card.of(HEART, CardNumber.ACE));
		cardBundle.addCard(Card.of(HEART, CardNumber.EIGHT));
		cardBundle.addCard(Card.of(HEART, number));

		//when
		int actual = cardBundle.calculateScore();

		//then
		assertThat(actual).isEqualTo(expect);
	}

	@DisplayName("두 카드패를 비교해서 승자, 패자, 무승부를 결정")
	@ParameterizedTest
	@CsvSource(value = {"TEN,WIN", "NINE,DRAW", "TWO,LOSE"})
	void compare(CardNumber number, GameResult expect) {
		//given
		CardBundle dealerBundle = new CardBundle();
		dealerBundle.addCard(Card.of(HEART, CardNumber.NINE));

		CardBundle gamblerBundle = new CardBundle();
		gamblerBundle.addCard(Card.of(HEART, number));

		//when
		GameResult result = dealerBundle.calculateWinOrLose(gamblerBundle);

		assertThat(result).isEqualTo(expect);
	}

	@DisplayName("버스트인사람은 무조건 패배")
	@ParameterizedTest
	@CsvSource(value = {"ACE,TWO,LOSE", "TWO,ACE,WIN"})
	void compare2(CardNumber dealerNumber, CardNumber gamblerNumber, GameResult expect) {
		//given
		CardBundle dealerCardBundle = new CardBundle();
		dealerCardBundle.addCard(Card.of(HEART, CardNumber.TEN));
		dealerCardBundle.addCard(Card.of(HEART, CardNumber.TEN));
		dealerCardBundle.addCard(Card.of(HEART, dealerNumber));

		CardBundle gamblerCardBundle = new CardBundle();
		gamblerCardBundle.addCard(Card.of(HEART, CardNumber.TEN));
		gamblerCardBundle.addCard(Card.of(HEART, CardNumber.TEN));
		gamblerCardBundle.addCard(Card.of(HEART, gamblerNumber));

		//when
		GameResult result = dealerCardBundle.calculateWinOrLose(gamblerCardBundle);

		assertThat(result).isEqualTo(expect);
	}

	@DisplayName("카드번들에서 첫번째카드를 제대로 꺼내는지 확인")
	@Test
	void getFirstCard() {
		CardBundle cardBundle = new CardBundle();
		cardBundle.addCard(Card.of(HEART, CardNumber.TEN));
		cardBundle.addCard(Card.of(HEART, CardNumber.TEN));

		Card actualCard = cardBundle.getFirstCard();

		assertThat(actualCard).isEqualTo(Card.of(HEART, CardNumber.TEN));
	}

	@DisplayName("카드번들이 갖고있는 카드가 블랙잭인지 확인")
	@ParameterizedTest
	@MethodSource("cardsProvider")
	void isBlackjack(List<Card> givenCards, boolean expect) {
		CardBundle cardBundle = new CardBundle();
		cardBundle.addCard(Card.of(HEART, CardNumber.TEN));
		for (Card card : givenCards) {
			cardBundle.addCard(card);
		}
		assertThat(cardBundle.isBlackjack()).isEqualTo(expect);
	}

	@DisplayName("카드번들이 갖고있는 카드가 버스트인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"ACE,false", "TWO,true"})
	void isBurst(CardNumber cardNumber, boolean expect) {
		CardBundle cardBundle = new CardBundle();
		cardBundle.addCard(Card.of(HEART, CardNumber.TEN));
		cardBundle.addCard(Card.of(HEART, CardNumber.TEN));
		cardBundle.addCard(Card.of(HEART, cardNumber));

		assertThat(cardBundle.isBurst()).isEqualTo(expect);
	}

	@DisplayName("카드번들이 갖고있는 카드가 버스트가 아닌지 확인")
	@ParameterizedTest
	@CsvSource(value = {"TWO,false", "ACE,true"})
	void isNotBurst(CardNumber cardNumber, boolean expect) {
		CardBundle cardBundle = new CardBundle();
		cardBundle.addCard(Card.of(HEART, CardNumber.TEN));
		cardBundle.addCard(Card.of(HEART, CardNumber.TEN));
		cardBundle.addCard(Card.of(HEART, cardNumber));

		assertThat(cardBundle.isNotBurst()).isEqualTo(expect);
	}

	@Test
	void isSameScore() {
		CardBundle cardBundle = new CardBundle();
		cardBundle.addCard(Card.of(HEART, CardNumber.TEN));
		cardBundle.addCard(Card.of(HEART, CardNumber.TEN));

		CardBundle other = new CardBundle();
		other.addCard(Card.of(DIAMOND, CardNumber.TEN));
		other.addCard(Card.of(CLUB, CardNumber.TEN));

		assertThat(cardBundle.isSameScore(other)).isTrue();
	}
}