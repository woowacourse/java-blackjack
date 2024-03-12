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

class PlayerLoseMatcherTest {
    private final PlayerResultMatcher playerResultMatcher = new PlayerLoseMatcher();

    @DisplayName("플레이어만 Bust인 경우 MATCH를 반환한다.")
    @ParameterizedTest
    @MethodSource("provideOnlyPlayerBust")
    void onlyPlayerBustTest(List<Number> playerNumbers, List<Number> dealerNumbers) {
        Player player = ParticipantGenerator.createPlayer(playerNumbers);
        Dealer dealer = ParticipantGenerator.createDealer(dealerNumbers);

        assertThat(playerResultMatcher.match(player, dealer))
                .isEqualTo(MatchResult.MATCH);
    }

    private static Stream<Arguments> provideOnlyPlayerBust() {
        return Stream.of(
                Arguments.of(List.of(Number.JACK, Number.JACK, Number.FOUR), List.of(Number.FIVE, Number.SIX)),
                Arguments.of(List.of(Number.ACE, Number.QUEEN, Number.KING, Number.ACE), List.of(Number.ACE, Number.ACE, Number.NINE)),
                Arguments.of(List.of(Number.FIVE, Number.EIGHT, Number.NINE), List.of(Number.QUEEN, Number.NINE))
        );
    }

    @DisplayName("딜러가 버스트가 아니면서 플레이어의 점수가 딜러보다 적을 경우 MATCH를 반환한다.")
    @ParameterizedTest
    @MethodSource("providePlayerLessScore")
    void playerLessScoreTest(List<Number> playerNumbers, List<Number> dealerNumbers) {
        Player player = ParticipantGenerator.createPlayer(playerNumbers);
        Dealer dealer = ParticipantGenerator.createDealer(dealerNumbers);

        assertThat(playerResultMatcher.match(player, dealer))
                .isEqualTo(MatchResult.MATCH);
    }

    private static Stream<Arguments> providePlayerLessScore() {
        return Stream.of(
                Arguments.of(List.of(Number.TWO, Number.FIVE), List.of(Number.FIVE, Number.SIX)),
                Arguments.of(List.of(Number.ACE, Number.SEVEN), List.of(Number.KING, Number.JACK))
        );
    }

    @DisplayName("딜러만 NaturalBlackjack인 경우 MATCH를 반환한다.")
    @ParameterizedTest
    @MethodSource("provideOnlyDealerNaturalBlackjack")
    void onlyDealerNaturalBlackjackTest(List<Number> playerNumbers, List<Number> dealerNumbers) {
        Player player = ParticipantGenerator.createPlayer(playerNumbers);
        Dealer dealer = ParticipantGenerator.createDealer(dealerNumbers);

        assertThat(playerResultMatcher.match(player, dealer))
                .isEqualTo(MatchResult.MATCH);
    }

    private static Stream<Arguments> provideOnlyDealerNaturalBlackjack() {
        return Stream.of(
                Arguments.of(List.of(Number.TWO, Number.FIVE), List.of(Number.ACE, Number.JACK)),
                Arguments.of(List.of(Number.ACE, Number.FIVE, Number.FIVE), List.of(Number.KING, Number.ACE))
        );
    }

    @DisplayName("플레이어가 이긴 게 아닐 경우 NOT_MATCH를 반환한다.")
    @ParameterizedTest
    @MethodSource("providePlayerNotLose")
    void playerNotLoseTest(List<Number> playerNumbers, List<Number> dealerNumbers) {
        Player player = ParticipantGenerator.createPlayer(playerNumbers);
        Dealer dealer = ParticipantGenerator.createDealer(dealerNumbers);

        assertThat(playerResultMatcher.match(player, dealer))
                .isEqualTo(MatchResult.NOT_MATCH);
    }

    private static Stream<Arguments> providePlayerNotLose() {
        return Stream.of(
                Arguments.of(List.of(Number.TWO, Number.EIGHT, Number.KING), List.of(Number.FIVE, Number.FIVE)),
                Arguments.of(List.of(Number.ACE, Number.FIVE, Number.FIVE), List.of(Number.FOUR, Number.TWO))
        );
    }
}
