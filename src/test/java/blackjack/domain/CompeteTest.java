package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.factory.CardMockFactory;
import blackjack.domain.util.CreateHand;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Compete 테스트")
class CompeteTest {

	@DisplayName("각자의 패에 따라서 누가 이기는지를 판단하는 테스트")
	@ParameterizedTest(name = "{index} {displayName} expectedOutcome={2}")
	@MethodSource("createHandAndOutcome")
	void check_Judge_Compete(Hand playerHand, Hand dealerHand, Outcome expectedOutcome) {
		Compete compete = new Compete();
		Role player = new Player("player", playerHand);
		Role dealer = new Dealer(dealerHand, DealerDrawable::chooseDraw);
		compete.judgeCompete(player, dealer);
		assertThat(compete.getPlayerCompeteResults(player)).isEqualTo(expectedOutcome);
	}

	@DisplayName("플레이어의 결과를 이용하여 딜러의 결과를 계산하는 테스트")
	@ParameterizedTest(name = "{index} {displayName} expectedOutcome={2}")
	@MethodSource("createPlayersAndOutcome")
	void check_Dealer_Compete_Results(List<Role> players, Hand dealerHand, Map<Outcome, Long> expectedOutcome) {
		Compete compete = new Compete();
		Role dealer = new Dealer(dealerHand, DealerDrawable::chooseDraw);
		for (Role player : players) {
			compete.judgeCompete(player, dealer);
		}
		assertThat(compete.getDealerCompeteResults()).isEqualTo(expectedOutcome);
	}

	private static Stream<Arguments> createHandAndOutcome() {
		final Hand winHand = CreateHand.create(CardMockFactory.of("A클로버"), CardMockFactory.of("K클로버"));
		final Hand looseHand = CreateHand.create(CardMockFactory.of("2클로버"), CardMockFactory.of("3클로버"));

		return Stream.of(
				Arguments.of(winHand, looseHand, Outcome.VICTORY),
				Arguments.of(looseHand, winHand, Outcome.DEFEAT),
				Arguments.of(winHand, winHand, Outcome.TIE));
	}

	private static Stream<Arguments> createPlayersAndOutcome() {
		final Hand firstHand = CreateHand.create(CardMockFactory.of("A클로버"), CardMockFactory.of("K클로버"));
		final Hand secondHand = CreateHand.create(CardMockFactory.of("K클로버"), CardMockFactory.of("J클로버"));
		final Hand thirdHand = CreateHand.create(CardMockFactory.of("2클로버"), CardMockFactory.of("3클로버"));

		Role firstPlayer = new Player("player1", firstHand);
		Role secondPlayer = new Player("player2", secondHand);
		Role thirdPlayer = new Player("player3", thirdHand);

		return Stream.of(
				Arguments.of(List.of(secondPlayer, thirdPlayer), firstHand,
						createOutcomeMap(2L, 0L, 0L)),
				Arguments.of(List.of(firstPlayer, secondPlayer), thirdHand,
						createOutcomeMap(0L, 2L, 0L)),
				Arguments.of(List.of(firstPlayer), firstHand,
						createOutcomeMap(0L, 0L, 1L)),
				Arguments.of(List.of(firstPlayer, secondPlayer, thirdPlayer), secondHand,
						createOutcomeMap(1L, 1L, 1L)));
	}

	private static Map<Outcome, Long> createOutcomeMap(Long victory, Long defeat, Long tie) {
		Map<Outcome, Long> outcomes = new EnumMap<>(Outcome.class);
		if (victory != 0L) {
			outcomes.put(Outcome.VICTORY, victory);
		}
		if (defeat != 0L) {
			outcomes.put(Outcome.DEFEAT, defeat);
		}
		if (tie != 0L) {
			outcomes.put(Outcome.TIE, tie);
		}
		return outcomes;
	}
}