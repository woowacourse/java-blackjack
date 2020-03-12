package blackjack.player.card;

import blackjack.player.card.component.CardNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static blackjack.player.card.component.Symbol.HEART;
import static org.assertj.core.api.Assertions.assertThat;

class CardBundleTest {

	@DisplayName("카드의 점수 계산 로직, ACE가 있는 경우에 21을 넘지 않으면 10을 더한 점수를 반환한다.")
	@ParameterizedTest
	@CsvSource(value = {"ACE, 20", "TWO,21"})
	void calculateScore(CardNumber number, int result) {
		//given
		CardBundle cardBundle = new CardBundle();
		cardBundle.addCard(Card.of(HEART, CardNumber.ACE));
		cardBundle.addCard(Card.of(HEART, CardNumber.EIGHT));
		cardBundle.addCard(Card.of(HEART, number));

		//when
		int expect = cardBundle.calculateScore();

		//then
		assertThat(expect).isEqualTo(result);
	}

	@DisplayName("두 카드패를 비교해서 승자, 패자, 무승부를 결정")
	@ParameterizedTest
	@CsvSource(value = {"TEN,WIN", "NINE,DRAW", "TWO,LOSE"})
	void compare(CardNumber number, GameResult result) {
		//given
		CardBundle dealerBundle = new CardBundle();
		dealerBundle.addCard(new Card(HEART, CardNumber.NINE));

		CardBundle gamblerBundle = new CardBundle();
		gamblerBundle.addCard(new Card(HEART, number));

		//when
		GameResult expect = dealerBundle.compare(gamblerBundle);

		assertThat(expect).isEqualTo(result);
	}

	@DisplayName("버스트인사람은 무조건 패배")
	@ParameterizedTest
	@CsvSource(value = {"ACE,TWO,LOSE", "TWO,ACE,WIN"})
	void compare2(CardNumber dealerNumber, CardNumber gamblerNumber, GameResult result) {
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
		GameResult expect = dealerCardBundle.compare(gamblerCardBundle);

		assertThat(expect).isEqualTo(result);
	}
}