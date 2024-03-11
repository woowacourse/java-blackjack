package blackjack.domain.result.checker;

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

class PlayerWinMatcherTest {
    private final PlayerResultMatcher playerResultMatcher = new PlayerWinMatcher();

    @DisplayName("딜러가 Bust면 MATCH를 반환한다.")
    @ParameterizedTest
    @MethodSource("provideDealerBust")
    void dealerBustTest(List<Number> playerNumbers, List<Number> dealerNumbers) {
        Player player = ParticipantGenerator.createPlayer(playerNumbers);
        Dealer dealer = ParticipantGenerator.createDealer(dealerNumbers);

        assertThat(playerResultMatcher.match(player, dealer))
                .isEqualTo(MatchResult.MATCH);
    }

    private static Stream<Arguments> provideDealerBust() {
        return Stream.of(
                Arguments.of(List.of(Number.JACK, Number.JACK), List.of(Number.FIVE, Number.SIX, Number.JACK, Number.KING)),
                Arguments.of(List.of(Number.ACE, Number.QUEEN, Number.KING), List.of(Number.ACE, Number.ACE, Number.NINE, Number.KING, Number.FIVE)),
                Arguments.of(List.of(Number.EIGHT, Number.NINE), List.of(Number.QUEEN, Number.NINE, Number.JACK))
        );
    }

    @DisplayName("플레이어가 NaturalBlackjack인 경우 NOT_MATCH를 반환한다.")
    @ParameterizedTest
    @MethodSource("providePlayerNaturalBlackjack")
    void playerIsNaturalBlackjackTest(List<Number> playerNumbers, List<Number> dealerNumbers) {
        Player player = ParticipantGenerator.createPlayer(playerNumbers);
        Dealer dealer = ParticipantGenerator.createDealer(dealerNumbers);

        assertThat(playerResultMatcher.match(player, dealer))
                .isEqualTo(MatchResult.NOT_MATCH);
    }

    private static Stream<Arguments> providePlayerNaturalBlackjack() {
        return Stream.of(
                Arguments.of(List.of(Number.JACK, Number.ACE), List.of(Number.FIVE, Number.JACK)),
                Arguments.of(List.of(Number.ACE, Number.QUEEN), List.of(Number.ACE, Number.NINE))
        );
    }

    @DisplayName("플레이어가 이기고 있는 경우 MATCH를 반환한다.")
    @ParameterizedTest
    @MethodSource("provideWinningPlayer")
    void playerIsWinningTest(List<Number> playerNumbers, List<Number> dealerNumbers) {
        Player player = ParticipantGenerator.createPlayer(playerNumbers);
        Dealer dealer = ParticipantGenerator.createDealer(dealerNumbers);

        assertThat(playerResultMatcher.match(player, dealer))
                .isEqualTo(MatchResult.MATCH);
    }

    private static Stream<Arguments> provideWinningPlayer() {
        return Stream.of(
                Arguments.of(List.of(Number.JACK, Number.JACK), List.of(Number.FIVE, Number.JACK)),
                Arguments.of(List.of(Number.ACE, Number.NINE), List.of(Number.ACE, Number.FIVE))
        );
    }
}