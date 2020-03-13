package blackjack.player.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.card.domain.Card;
import blackjack.card.domain.CardBundle;
import blackjack.card.domain.GameResult;
import blackjack.card.domain.component.CardNumber;
import blackjack.card.domain.component.Symbol;
import blackjack.player.domain.report.GameReport;

class DealerTest {

	@DisplayName("딜러와 겜블러가 비교하여 결과를 만들어 낸다.")
	@ParameterizedTest
	@CsvSource(value = {"TWO,LOSE", "FIVE,DRAW", "SIX,WIN"})
	void getReport(CardNumber cardNumber, GameResult result) {
		//given
		Player dealer = new Dealer(new CardBundle());
		dealer.addCard(Card.of(Symbol.DIAMOND, CardNumber.FIVE));

		Player gambler = new Gambler(new CardBundle(), "bebop");
		gambler.addCard(Card.of(Symbol.DIAMOND, cardNumber));

		//when
		GameReport report = dealer.createReport(gambler);

		//then
		assertThat(report).isEqualTo(new GameReport("bebop", result));
	}

	@DisplayName("딜러의 카드패가 16을 초과하면 뽑을수 없다.")
	@ParameterizedTest
	@CsvSource(value = {"TEN,false", "NINE,true"})
	void isDrawable(CardNumber cardNumber, boolean result) {
		//given
		Player dealer = new Dealer(new CardBundle());
		dealer.addCard(Card.of(Symbol.DIAMOND, CardNumber.SEVEN));
		dealer.addCard(Card.of(Symbol.DIAMOND, cardNumber));

		//when
		boolean drawable = dealer.isDrawable();

		//then
		assertThat(drawable).isEqualTo(result);
	}
}