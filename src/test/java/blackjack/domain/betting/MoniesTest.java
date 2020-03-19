package blackjack.domain.betting;

import blackjack.domain.betting.exceptions.MoniesException;
import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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
	private static List<Money> threeBettingMonies;
	private static Players players;
	private static Playable dealer;
	private static Results results;

	private Monies monies;

	@BeforeAll
	static void beforeAll() {
		zero = Money.zero();
		hundred = Money.of("100");
		thousand = Money.of("1000");
		fiveThousand = Money.of("5000");
		threeBettingMonies = Arrays.asList(hundred, thousand, fiveThousand);

		players = Players.of("그니,포비,씨유");
		players.getPlayers().get(0).receiveCard(Card.of(Symbol.TEN, Type.DIAMOND));
		players.getPlayers().get(1).receiveCards(Arrays.asList(Card.of(Symbol.ACE, Type.CLUB),
				Card.of(Symbol.KING, Type.HEART)));
		players.getPlayers().get(2).receiveCard(Card.of(Symbol.TWO, Type.DIAMOND));
		dealer = Dealer.dealer();
		dealer.receiveCard(Card.of(Symbol.ACE, Type.SPADE));

		results = Results.of(dealer, players);
	}

	@BeforeEach
	void setup() {
		monies = Monies.of(players, threeBettingMonies);
	}

	@ParameterizedTest
	@MethodSource("of_IsNotNull")
	void of_IsNotNull(List<Money> inputs) {
		assertThat(Monies.of(players, inputs)).isNotNull();
	}

	static Stream<List<Money>> of_IsNotNull() {
		return Stream.of(threeBettingMonies,
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

	@Test
	void computeGameResultMonies() {
		assertThat(monies.computeGameResultMonies(results))
				.isEqualTo(Monies.of(players,
						Arrays.asList(Money.of("-100"),
								Money.of("1500"),
								Money.of("-5000"))));
	}

	@Test
	void ketSet() {
		Name[] expect = new Name[players.memberSize()];
		for (int i = 0; i < players.memberSize(); i++) {
			expect[i] = players.getPlayers().get(i).getName();
		}

		assertThat(monies.keySet()).containsExactly(expect);
	}

	@ParameterizedTest
	@MethodSource("get")
	void get(Playable player, Money expect) {
		assertThat(monies.getMoney(player.getName())).isEqualTo(expect);
	}

	static Stream<Arguments> get() {
		return Stream.of(Arguments.of(players.getPlayers().get(0), hundred),
				Arguments.of(players.getPlayers().get(1), thousand),
				Arguments.of(players.getPlayers().get(2), fiveThousand));
	}

}