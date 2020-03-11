package blackjack.player.card;

import static blackjack.player.card.component.Symbol.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.player.card.component.CardNumber;

class CardBundleTest {

	@DisplayName("카드의 점수 계산 로직, ACE가 있는 경우에 21을 넘지 않으면 10을 더한 점수를 반환한다.")
	@ParameterizedTest
	@CsvSource(value = {"ACE, 20", "TWO,21"})
	void calculateScore(CardNumber number, int result) {
		//given
		CardBundle cardBundle = new CardBundle();
		cardBundle.addCard(new Card(HEART, CardNumber.ACE));
		cardBundle.addCard(new Card(HEART, CardNumber.EIGHT));
		cardBundle.addCard(new Card(HEART, number));

		//when
		int expect = cardBundle.calculateScore();

		//then
		assertThat(expect).isEqualTo(result);
	}

	@DisplayName("두 카드패를 비교해서 승자, 패자, 무승부를 결정")
	@ParameterizedTest
	@CsvSource(value = {"TEN,-1", "NINE,0", "TWO,1"})
	void compare(CardNumber number, int result) {
		//given
		CardBundle cardBundle = new CardBundle();
		cardBundle.addCard(new Card(HEART, CardNumber.NINE));

		CardBundle other = new CardBundle();
		other.addCard(new Card(HEART, number));

		//when
		int expect = cardBundle.compare(other);

		assertThat(expect).isEqualTo(result);
	}

	@DisplayName("버스트인사람은 무조건 패배")
	@ParameterizedTest
	@CsvSource(value = {"ACE,TWO,-1", "TWO,ACE,1"})
	void compare2(CardNumber dealerNumber, CardNumber gamblerNumber, int result) {
		//given
		CardBundle dealerCardBundle = new CardBundle();
		dealerCardBundle.addCard(new Card(HEART, CardNumber.TEN));
		dealerCardBundle.addCard(new Card(HEART, CardNumber.TEN));
		dealerCardBundle.addCard(new Card(HEART, dealerNumber));

		CardBundle gamblerCardBundle = new CardBundle();
		gamblerCardBundle.addCard(new Card(HEART, CardNumber.TEN));
		gamblerCardBundle.addCard(new Card(HEART, CardNumber.TEN));
		gamblerCardBundle.addCard(new Card(HEART, gamblerNumber));

		//when
		int expect = dealerCardBundle.compare(gamblerCardBundle);

		assertThat(expect).isEqualTo(result);
	}
}