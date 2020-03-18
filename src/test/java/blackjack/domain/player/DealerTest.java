package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.card.component.CardNumber;
import blackjack.domain.card.component.Symbol;
import blackjack.domain.generic.BettingMoney;
import blackjack.domain.report.GameReport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

	@DisplayName("딜러와 겜블러가 비교하여 결과를 만들어 낸다.")
	@ParameterizedTest
	@CsvSource(value = {"TWO,-1000", "FIVE,0", "SIX,1000"})
	void getReport(CardNumber cardNumber, double resultMoney) {
		//given
		Dealer dealer = new Dealer(CardBundle.emptyBundle());
		dealer.drawCard(() -> Card.of(Symbol.DIAMOND, CardNumber.FIVE));

		Gambler gambler = new Gambler(CardBundle.emptyBundle(), new PlayerInfo("bebop", BettingMoney.of(1000)));
		gambler.drawCard(() -> Card.of(Symbol.DIAMOND, cardNumber));

		//when
		GameReport report = dealer.createReport(gambler);

		//then
		assertThat(report.getName()).isEqualTo("bebop");
		assertThat(report.getMoney()).isEqualTo(resultMoney);
	}

	@DisplayName("딜러의 카드패가 16을 초과하면 뽑을수 없다.")
	@ParameterizedTest
	@CsvSource(value = {"TEN,false", "NINE,true"})
	void isDrawable(CardNumber cardNumber, boolean result) {
		//given
		Player dealer = new Dealer(CardBundle.emptyBundle());
		dealer.drawCard(() -> Card.of(Symbol.DIAMOND, CardNumber.SEVEN));
		dealer.drawCard(() -> Card.of(Symbol.DIAMOND, cardNumber));

		//when
		boolean drawable = dealer.isDrawable();

		//then
		assertThat(drawable).isEqualTo(result);
	}
}