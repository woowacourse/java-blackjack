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

class GamblerWinStrategyTest {
	private static Stream<Arguments> cardBundleProvider() {
		return Stream.of(
			Arguments.of(
				aCardBundle(CardNumber.TEN), aCardBundle(CardNumber.NINE)
			),
			Arguments.of(
				aCardBundle(CardNumber.TEN, CardNumber.TEN), aCardBundle(CardNumber.TEN, CardNumber.TEN, CardNumber.TEN)
			)
		);
	}

	@DisplayName("두개의 카드번들을 비교하여 플레이어가 일반 승릴를 하는 경우")
	@ParameterizedTest
	@MethodSource("cardBundleProvider")
	void isResult(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
		//given
		GameResultStrategy gamblerLoseStrategy = new GamblerWinStrategy();
		//when
		boolean actual = gamblerLoseStrategy.isResult(dealerCardBundle, gamblerCardBundle);
		//then
		assertThat(actual).isTrue();
	}
}