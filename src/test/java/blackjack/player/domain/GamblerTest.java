package blackjack.player.domain;

import static blackjack.card.domain.CardBundleHelper.*;
import static blackjack.player.domain.component.PlayerInfoHelper.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.card.domain.CardBundle;
import blackjack.card.domain.component.CardNumber;

class GamblerTest {

	private static Stream<Arguments> bundleProvider() {
		return Stream.of(
			Arguments.of(
				true, aCardBundle(CardNumber.TEN)
			),
			Arguments.of(
				false, aCardBundle(CardNumber.TEN, CardNumber.ACE)
			),
			Arguments.of(
				false, aCardBundle(CardNumber.TEN, CardNumber.TEN, CardNumber.TWO)
			)
		);
	}

	@DisplayName("겜블러는 21이상 스코어면 카드를 뽑을수 없다.")
	@ParameterizedTest
	@MethodSource("bundleProvider")
	void isDrawable(boolean expect, CardBundle cardBundle) {
		Player gambler = new Gambler(cardBundle, aPlayerInfo("allen"));
		assertThat(gambler.isHit()).isEqualTo(expect);
	}
}