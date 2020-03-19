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

class DrawStrategyTest {

	private static Stream<Arguments> cardBundleProvider() {
		return Stream.of(
			Arguments.of(
				aCardBundle(CardNumber.TEN), aCardBundle(CardNumber.TEN)
			),
			Arguments.of(
				aCardBundle(CardNumber.TEN, CardNumber.ACE), aCardBundle(CardNumber.TEN, CardNumber.ACE)
			)
		);
	}

	@DisplayName("두개의 카드번들을 비교하여 무승부인지 확인")
	@ParameterizedTest
	@MethodSource("cardBundleProvider")
	void isResult(CardBundle gambler, CardBundle dealer) {
		GameResultStrategy drawStrategy = new DrawStrategy();

		assertThat(drawStrategy.isResult(gambler, dealer)).isTrue();
	}
}