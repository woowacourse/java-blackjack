package blackjack.player.domain;

import static blackjack.card.domain.CardBundleHelper.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

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
		Player gambler = new Gambler(cardBundle, "allen", Money.create(1000));
		assertThat(gambler.isHit()).isEqualTo(expect);
	}

	@DisplayName("겜블러의 이름이 빈 값이나 널이면 Exception")
	@ParameterizedTest
	@NullAndEmptySource
	void name(String name) {
		assertThatThrownBy(() -> new Gambler(new CardBundle(), name, Money.create(1000)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("겜블러의 Cardbundle이 널이면 Exception")
	@ParameterizedTest
	@NullSource
	void test2(CardBundle cardBundle) {
		assertThatThrownBy(() -> new Gambler(cardBundle, "allen", Money.create(1000)))
			.isInstanceOf(NullPointerException.class);
	}

	@DisplayName("겜블러의 배팅비용이 0원이하면 Exception")
	@ParameterizedTest
	@CsvSource(value = {"0", "-1"})
	void test3(int bettingMoney) {
		assertThatThrownBy(() -> new Gambler(new CardBundle(), "allen", Money.create(bettingMoney)))
			.isInstanceOf(IllegalArgumentException.class);
	}
}