package blackjack.player.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static blackjack.player.card.Card.Symbol.HEART;
import static org.assertj.core.api.Assertions.assertThat;

class CardBundleTest {

	@DisplayName("카드의 점수 계산 로직, ACE가 있는 경우에 21을 넘지 않으면 10을 더한 점수를 반환한다.")
	@ParameterizedTest
	@CsvSource(value = {"ACE, 20", "TWO,21"})
	void calculateScore(Card.CardNumber number, int result) {
		//given
		CardBundle cardBundle = new CardBundle();
		cardBundle.addCard(Card.valueOf(HEART, Card.CardNumber.ACE));
		cardBundle.addCard(Card.valueOf(HEART, Card.CardNumber.EIGHT));
		cardBundle.addCard(Card.valueOf(HEART, number));

		//when
		int expect = cardBundle.calculateScore();

		//then
		assertThat(expect).isEqualTo(result);
	}

	@DisplayName("두 카드패를 비교해서 승자, 패자, 무승부를 결정")
	@ParameterizedTest
	@CsvSource(value = {"TEN,-1", "NINE,0", "ACE,1"})
	void compare(Card.CardNumber number, int result) {
		//given
		CardBundle cardBundle = new CardBundle();
		cardBundle.addCard(Card.valueOf(HEART, Card.CardNumber.NINE));

		CardBundle other = new CardBundle();
		other.addCard(Card.valueOf(HEART, number));

		//when
		int expect = cardBundle.compare(other);

		assertThat(expect).isEqualTo(result);
	}
}