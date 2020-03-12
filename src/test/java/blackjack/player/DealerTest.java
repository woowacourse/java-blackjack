package blackjack.player;

import blackjack.GameReport;
import blackjack.player.card.Card;
import blackjack.player.card.CardBundle;
import blackjack.player.card.GameResult;
import blackjack.player.card.component.CardNumber;
import blackjack.player.card.component.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

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
}