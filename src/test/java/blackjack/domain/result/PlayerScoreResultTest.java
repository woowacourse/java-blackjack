package blackjack.domain.result;

import static blackjack.fixture.TestFixture.provide16Cards;
import static blackjack.fixture.TestFixture.provideBlackjack;
import static blackjack.fixture.TestFixture.provideEmptyCards;
import static blackjack.fixture.TestFixture.providePlayer;
import static blackjack.fixture.TestFixture.provideUnder16Cards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Hand;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerScoreResultTest {

    @ParameterizedTest
    @MethodSource
    void 우승_결과를_계산한다(final Hand dealerHand, final Hand playerHand, final ResultStatus resultStatus) {
        // Given
        final Dealer dealer = new Dealer(dealerHand);
        final Player player = providePlayer("밍트", 10_000);
        final Map<Player, Hand> playerScores = Map.of(player, playerHand);
        PlayerScoreResult playerScoreResult = new PlayerScoreResult(playerScores);

        // When & Then
        assertThat(playerScoreResult.calculateScoreResult(dealer)).isEqualTo(Map.of(player, resultStatus));
    }

    private static Stream<Arguments> 우승_결과를_계산한다() {
        return Stream.of(
                Arguments.of(provideBlackjack(), provideBlackjack(), ResultStatus.PUSH),
                Arguments.of(provideBlackjack(), provideUnder16Cards(), ResultStatus.LOSE),
                Arguments.of(provideUnder16Cards(), provideBlackjack(), ResultStatus.BLACKJACK),
                Arguments.of(provideUnder16Cards(), provideEmptyCards(), ResultStatus.LOSE),
                Arguments.of(provideEmptyCards(), provideUnder16Cards(), ResultStatus.WIN),
                Arguments.of(provide16Cards(), provide16Cards(), ResultStatus.PUSH)
        );
    }
}
