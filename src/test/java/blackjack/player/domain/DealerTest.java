package blackjack.player.domain;

//todo : 지금은 승, 패로 되어있는데 profit을 뱉도록 변경해야함.

import static blackjack.player.domain.component.PlayerInfoHelper.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.card.domain.Card;
import blackjack.card.domain.CardBundle;
import blackjack.card.domain.GameResult;
import blackjack.card.domain.component.CardNumber;
import blackjack.card.domain.component.Symbol;
import blackjack.player.domain.report.GameReport;

class DealerTest {

	private static Stream<Arguments> gameReportProvider() {
		// {"TWO,LOSE", "FIVE,DRAW", "SIX,WIN"
		return Stream.of(
			Arguments.of(
				CardNumber.ACE, new GameReport(aPlayerInfo("bebop"), GameResult.BLACKJACK_WIN)
			),
			Arguments.of(
				CardNumber.FOUR, new GameReport(aPlayerInfo("bebop"), GameResult.WIN)
			),
			Arguments.of(
				CardNumber.THREE, new GameReport(aPlayerInfo("bebop"), GameResult.DRAW)
			),
			Arguments.of(
				CardNumber.TWO, new GameReport(aPlayerInfo("bebop"), GameResult.LOSE)
			)
		);
	}

	@DisplayName("딜러와 겜블러가 비교하여 GameReport를 만들어 낸다.")
	@ParameterizedTest
	@MethodSource("gameReportProvider")
	void getReport(CardNumber cardNumber, GameReport expect) {
		//given
		Dealer dealer = new Dealer(new CardBundle());
		dealer.addCard(Card.of(Symbol.DIAMOND, CardNumber.TEN));
		dealer.addCard(Card.of(Symbol.DIAMOND, CardNumber.THREE));

		Player gambler = new Gambler(new CardBundle(), aPlayerInfo("bebop"));
		gambler.addCard(Card.of(Symbol.DIAMOND, CardNumber.TEN));
		gambler.addCard(Card.of(Symbol.DIAMOND, cardNumber));

		//when
		GameReport report = dealer.createReport((Gambler)gambler);

		//then
		assertThat(report).isEqualTo(expect);
	}

	@DisplayName("딜러의 카드패가 16을 초과하면 뽑을수 없다.")
	@ParameterizedTest
	@CsvSource(value = {"TEN,false", "NINE,true"})
	void isDrawable(CardNumber cardNumber, boolean expect) {
		//given
		Player dealer = new Dealer(new CardBundle());
		dealer.addCard(Card.of(Symbol.DIAMOND, CardNumber.SEVEN));
		dealer.addCard(Card.of(Symbol.DIAMOND, cardNumber));

		//when
		boolean drawable = dealer.isHit();

		//then
		assertThat(drawable).isEqualTo(expect);
	}
}