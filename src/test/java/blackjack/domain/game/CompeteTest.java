package blackjack.domain.game;

import static blackjack.factory.HandMockFactory.getBlackJackHand;
import static blackjack.factory.HandMockFactory.getBottomHand;
import static blackjack.factory.HandMockFactory.getBustHand;
import static blackjack.factory.HandMockFactory.getNotBlackjackTopHand;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.role.Dealer;
import blackjack.domain.role.DealerDrawable;
import blackjack.domain.role.Hand;
import blackjack.domain.role.Player;
import blackjack.domain.role.Role;
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
		return Stream.of(
				Arguments.of(getBlackJackHand(), getNotBlackjackTopHand(),
						Outcome.BLACKJACK_VICTORY),
				Arguments.of(getNotBlackjackTopHand(), getBottomHand(), Outcome.VICTORY),
				Arguments.of(getBottomHand(), getBustHand(), Outcome.VICTORY),

				Arguments.of(getNotBlackjackTopHand(), getBlackJackHand(), Outcome.DEFEAT),
				Arguments.of(getBottomHand(), getBlackJackHand(), Outcome.DEFEAT),
				Arguments.of(getBottomHand(), getNotBlackjackTopHand(), Outcome.DEFEAT),
				Arguments.of(getBustHand(), getBottomHand(), Outcome.DEFEAT),
				Arguments.of(getBustHand(), getBustHand(), Outcome.DEFEAT),

				Arguments.of(getBlackJackHand(), getBlackJackHand(), Outcome.TIE),
				Arguments.of(getNotBlackjackTopHand(), getNotBlackjackTopHand(), Outcome.TIE),
				Arguments.of(getBottomHand(), getBottomHand(), Outcome.TIE));
	}
}