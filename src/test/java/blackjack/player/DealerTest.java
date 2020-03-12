package blackjack.player;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.card.Card;
import blackjack.card.CardBundle;
import blackjack.card.GameReport;
import blackjack.card.GameResult;
import blackjack.card.component.CardNumber;
import blackjack.card.component.Symbol;

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
		GameReport report = dealer.getReport(gambler);

		//then
		assertThat(report).isEqualTo(new GameReport("bebop", result));
	}

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