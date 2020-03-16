package blackjack.card.domain;

import static blackjack.card.domain.component.Symbol.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.card.domain.component.CardNumber;

class CardBundleTest {

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
	void compare(CardNumber number, GameResult exppect) {
		//given
		CardBundle dealerBundle = new CardBundle();
		dealerBundle.addCard(Card.of(HEART, CardNumber.NINE));

		CardBundle gamblerBundle = new CardBundle();
		gamblerBundle.addCard(Card.of(HEART, number));

		//when
		GameResult result = dealerBundle.calculateWinOrLose(gamblerBundle);

		assertThat(result).isEqualTo(exppect);
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
}