package blackjack.domain.betting;

import blackjack.domain.betting.exceptions.BettingMoniesException;
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

class BettingMoniesTest {
	private static BettingMoney zero;
	private static BettingMoney hundred;
	private static BettingMoney thousand;
	private static BettingMoney fiveThousand;
	private static List<BettingMoney> threeBettingMoney;

	private Players players;

	@BeforeAll
	static void beforeAll() {
		zero = BettingMoney.zero();
		hundred = BettingMoney.of("100");
		thousand = BettingMoney.of("1000");
		fiveThousand = BettingMoney.of("5000");
		threeBettingMoney = Arrays.asList(hundred, thousand, fiveThousand);
	}

	@BeforeEach
	void setup() {
		players = Players.of("그니,포비,씨유");
	}

	@ParameterizedTest
	@MethodSource("of_IsNotNull")
	void of_IsNotNull(List<BettingMoney> inputs) {
		assertThat(BettingMonies.of(players, inputs)).isNotNull();
	}

	static Stream<List<BettingMoney>> of_IsNotNull() {
		return Stream.of(threeBettingMoney,
				Arrays.asList(zero, zero, zero),
				Arrays.asList(hundred, hundred, hundred));
	}

	@ParameterizedTest
	@MethodSource("of_InvalidInput_ThrowBettingMoniesException")
	void of_InvalidInput_ThrowBettingMoniesException(List<BettingMoney> inputs) {
		assertThatThrownBy(() -> BettingMonies.of(players, inputs))
				.isInstanceOf(BettingMoniesException.class);
	}

	static Stream<List<BettingMoney>> of_InvalidInput_ThrowBettingMoniesException() {
		return Stream.of(Collections.emptyList(),
				Collections.singletonList(hundred),
				Arrays.asList(hundred, thousand),
				Arrays.asList(zero, hundred, thousand, fiveThousand),
				null);
	}
}