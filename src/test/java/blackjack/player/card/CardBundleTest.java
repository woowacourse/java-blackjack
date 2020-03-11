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
}