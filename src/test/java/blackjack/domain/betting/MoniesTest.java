package blackjack.domain.betting;

import blackjack.domain.betting.exceptions.MoniesException;
import blackjack.domain.user.Players;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoniesTest {
	private static Money zero;
	private static Money hundred;
	private static Money thousand;
	private static Money fiveThousand;
	private static List<Money> threeBettingMoney;

	private Players players;

	@BeforeAll
	static void beforeAll() {
		zero = Money.zero();
		hundred = Money.of("100");
		thousand = Money.of("1000");
		fiveThousand = Money.of("5000");
		threeBettingMoney = Arrays.asList(hundred, thousand, fiveThousand);
	}

	@BeforeEach
	void setup() {
		players = Players.of("그니,포비,씨유");
	}

	@ParameterizedTest
	@MethodSource("of_IsNotNull")
	void of_IsNotNull(List<Money> inputs) {
		assertThat(Monies.of(players, inputs)).isNotNull();
	}

	static Stream<List<Money>> of_IsNotNull() {
		return Stream.of(threeBettingMoney,
				Arrays.asList(zero, zero, zero),
				Arrays.asList(hundred, hundred, hundred));
	}

	@ParameterizedTest
	@MethodSource("of_InvalidInput_ThrowBettingMoniesException")
	void of_InvalidInput_ThrowBettingMoniesException(List<Money> inputs) {
		assertThatThrownBy(() -> Monies.of(players, inputs))
				.isInstanceOf(MoniesException.class);
	}

	static Stream<List<Money>> of_InvalidInput_ThrowBettingMoniesException() {
		return Stream.of(Collections.emptyList(),
				Collections.singletonList(hundred),
				Arrays.asList(hundred, thousand),
				Arrays.asList(zero, hundred, thousand, fiveThousand),
				null);
	}
}