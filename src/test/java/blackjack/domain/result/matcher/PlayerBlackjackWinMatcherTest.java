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

    @DisplayName("플레이어만 Blackjack일 경우 참을 반환한다.")
    @ParameterizedTest
    @MethodSource("provideBlackjackWin")
    void playerBlackjackLoseMatchTest(List<Number> playerNumbers, List<Number> dealerNumbers) {
        Player player = ParticipantGenerator.createPlayer(playerNumbers);
        Dealer dealer = ParticipantGenerator.createDealer(dealerNumbers);

        assertThat(playerResultMatcher.isResultMatched(player, dealer))
                .isEqualTo(true);
    }

    private static Stream<Arguments> provideBlackjackWin() {
        return Stream.of(
                Arguments.of(List.of(Number.ACE, Number.JACK), List.of(Number.FIVE, Number.SIX)),
                Arguments.of(List.of(Number.ACE, Number.QUEEN), List.of(Number.ACE, Number.ACE, Number.NINE)),
                Arguments.of(List.of(Number.ACE, Number.KING), List.of(Number.QUEEN, Number.QUEEN, Number.NINE))
        );
    }


    @DisplayName("플레이어만 Blackjack이 아닐 경우 거짓을 반환한다.")
    @ParameterizedTest
    @MethodSource("provideNotBlackjackWin")
    void playerNotBlackjackLoseMatchTest(List<Number> playerNumbers, List<Number> dealerNumbers) {
        Player player = ParticipantGenerator.createPlayer(playerNumbers);
        Dealer dealer = ParticipantGenerator.createDealer(dealerNumbers);

        assertThat(playerResultMatcher.isResultMatched(player, dealer))
                .isEqualTo(false);
    }

    private static Stream<Arguments> provideNotBlackjackWin() {
        return Stream.of(
                Arguments.of(List.of(Number.QUEEN, Number.JACK), List.of(Number.JACK, Number.ACE)),
                Arguments.of(List.of(Number.ACE, Number.QUEEN, Number.FIVE), List.of(Number.JACK, Number.ACE)),
                Arguments.of(List.of(Number.ACE, Number.ACE), List.of(Number.NINE, Number.QUEEN, Number.QUEEN))
        );
    }

}
