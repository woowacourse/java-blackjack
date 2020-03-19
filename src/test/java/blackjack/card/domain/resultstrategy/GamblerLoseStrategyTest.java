package blackjack.card.domain.resultstrategy;

import static blackjack.card.domain.CardBundleHelper.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.card.domain.CardBundle;
import blackjack.card.domain.component.CardNumber;

class GamblerLoseStrategyTest {

	private static Stream<Arguments> cardBundleProvider() {
		return Stream.of(
			Arguments.of(
				aCardBundle(CardNumber.NINE), aCardBundle(CardNumber.TEN)
			),
			Arguments.of(
				aCardBundle(CardNumber.TEN, CardNumber.TEN), aCardBundle(CardNumber.TEN, CardNumber.ACE)
			),
			Arguments.of(
				aCardBundle(CardNumber.TEN, CardNumber.TEN, CardNumber.TEN), aCardBundle(CardNumber.TEN, CardNumber.TEN)
			)
		);
	}

	@DisplayName("두개의 카드번들을 비교하여 겜블러가 지는 경우 확인")
	@ParameterizedTest
	@MethodSource("cardBundleProvider")
	void isResult(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
		//given
		GameResultStrategy gamblerLoseStrategy = new GamblerLoseStrategy();
		//when
		boolean actual = gamblerLoseStrategy.isResult(dealerCardBundle, gamblerCardBundle);
		//then
		assertThat(actual).isTrue();
	}
}