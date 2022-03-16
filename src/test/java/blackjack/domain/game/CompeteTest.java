package blackjack.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.role.Dealer;
import blackjack.domain.role.DealerDrawable;
import blackjack.domain.role.Hand;
import blackjack.domain.role.Player;
import blackjack.domain.role.Role;
import blackjack.factory.CardMockFactory;
import blackjack.util.CreateHand;
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

	private static Stream<Arguments> createHandAndOutcome() {
		final Hand blackJackHand = CreateHand.create(CardMockFactory.of("A클로버"), CardMockFactory.of("K클로버"));
		final Hand notBlackjackTopHand = CreateHand.create(CardMockFactory.of("2클로버"), CardMockFactory.of("K클로버"),
				CardMockFactory.of("9클로버"));
		final Hand bottomHand = CreateHand.create(CardMockFactory.of("2클로버"), CardMockFactory.of("3클로버"));
		final Hand bustHand = CreateHand.create(CardMockFactory.of("K클로버"), CardMockFactory.of("J클로버"),
				CardMockFactory.of("Q클로버"));

		return Stream.of(
				Arguments.of(blackJackHand, bottomHand, Outcome.BLACKJACK_VICTORY),
				Arguments.of(blackJackHand, notBlackjackTopHand, Outcome.BLACKJACK_VICTORY),
				Arguments.of(notBlackjackTopHand, bottomHand, Outcome.VICTORY),
				Arguments.of(bottomHand, bustHand, Outcome.VICTORY),

				Arguments.of(notBlackjackTopHand, blackJackHand, Outcome.DEFEAT),
				Arguments.of(bottomHand, blackJackHand, Outcome.DEFEAT),
				Arguments.of(bottomHand, notBlackjackTopHand, Outcome.DEFEAT),
				Arguments.of(bustHand, bottomHand, Outcome.DEFEAT),
				Arguments.of(bustHand, bustHand, Outcome.DEFEAT),

				Arguments.of(blackJackHand, blackJackHand, Outcome.TIE),
				Arguments.of(notBlackjackTopHand, notBlackjackTopHand, Outcome.TIE),
				Arguments.of(bottomHand, bottomHand, Outcome.TIE));
	}
}