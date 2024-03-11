package blackjack.domain.result.matcher;

import blackjack.domain.card.Number;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.testutil.ParticipantGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerBlackjackWinMatcherTest {
    private final PlayerResultMatcher playerResultMatcher = new PlayerBlackjackWinMatcher();

    @DisplayName("플레이어만 NaturalBlackjack일 경우 MATCH를 반환한다.")
    @ParameterizedTest
    @MethodSource("provideNaturalBlackjackWin")
    void playerBlackjackLoseMatchTest(List<Number> playerNumbers, List<Number> dealerNumbers) {
        Player player = ParticipantGenerator.createPlayer(playerNumbers);
        Dealer dealer = ParticipantGenerator.createDealer(dealerNumbers);

        assertThat(playerResultMatcher.match(player, dealer))
                .isEqualTo(MatchResult.MATCH);
    }

    private static Stream<Arguments> provideNaturalBlackjackWin() {
        return Stream.of(
                Arguments.of(List.of(Number.ACE, Number.JACK), List.of(Number.FIVE, Number.SIX)),
                Arguments.of(List.of(Number.ACE, Number.QUEEN), List.of(Number.ACE, Number.ACE, Number.NINE)),
                Arguments.of(List.of(Number.ACE, Number.KING), List.of(Number.QUEEN, Number.QUEEN, Number.NINE))
        );
    }


    @DisplayName("플레이어만 NaturalBlackjack이 아닐 경우 NOT_MATCH를 반환한다.")
    @ParameterizedTest
    @MethodSource("provideNotNaturalBlackjackWin")
    void playerNotBlackjackLoseMatchTest(List<Number> playerNumbers, List<Number> dealerNumbers) {
        Player player = ParticipantGenerator.createPlayer(playerNumbers);
        Dealer dealer = ParticipantGenerator.createDealer(dealerNumbers);

        assertThat(playerResultMatcher.match(player, dealer))
                .isEqualTo(MatchResult.NOT_MATCH);
    }

    private static Stream<Arguments> provideNotNaturalBlackjackWin() {
        return Stream.of(
                Arguments.of(List.of(Number.QUEEN, Number.JACK), List.of(Number.JACK, Number.ACE)),
                Arguments.of(List.of(Number.ACE, Number.QUEEN, Number.FIVE), List.of(Number.JACK, Number.ACE)),
                Arguments.of(List.of(Number.ACE, Number.ACE), List.of(Number.NINE, Number.QUEEN, Number.QUEEN))
        );
    }

}