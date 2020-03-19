package blackjack.card.domain;

import static blackjack.card.domain.component.Symbol.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.card.domain.component.CardNumber;

class GameResultTest {

	@DisplayName("GameResult에서 값(승,무,패) 찾기")
	@ParameterizedTest
	@CsvSource(value = {"ACE,BLACKJACK_WIN", "FOUR,WIN", "THREE,DRAW", "TWO,LOSE"})
	void findByResult(CardNumber cardNumber, GameResult expect) {
		//given
		CardBundle dealerBundle = new CardBundle();
		dealerBundle.addCard(Card.of(HEART, CardNumber.TEN));
		dealerBundle.addCard(Card.of(HEART, CardNumber.THREE));

		CardBundle gamblerBundle = new CardBundle();
		gamblerBundle.addCard(Card.of(HEART, CardNumber.TEN));
		gamblerBundle.addCard(Card.of(HEART, cardNumber));

		//when
		GameResult result = GameResult.createGameResult(dealerBundle, gamblerBundle);

		//then
		assertThat(result).isEqualTo(expect);
	}
}