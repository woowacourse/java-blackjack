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

class PlayerDrawMatcherTest {
    private final PlayerResultMatcher playerResultMatcher = new PlayerDrawMatcher();

    @DisplayName("플레이어와 딜러가 모두 Blackjack일 경우 참을 반환한다.")
    @ParameterizedTest
    @MethodSource("provideBothBlackjack")
    void bothBlackjackTest(List<Number> playerNumbers, List<Number> dealerNumbers) {
        Player player = ParticipantGenerator.createPlayer(playerNumbers);
        Dealer dealer = ParticipantGenerator.createDealer(dealerNumbers);

        assertThat(playerResultMatcher.isResultMatched(player, dealer))
                .isEqualTo(true);
    }

    private static Stream<Arguments> provideBothBlackjack() {
        return Stream.of(
                Arguments.of(List.of(Number.ACE, Number.JACK), List.of(Number.QUEEN, Number.ACE)),
                Arguments.of(List.of(Number.KING, Number.ACE), List.of(Number.JACK, Number.ACE))
        );
    }

    @DisplayName("점수가 같지만, 플레이어와 딜러 중 한쪽만 Blackjack일 경우 거짓을 반환한다.")
    @ParameterizedTest
    @MethodSource("provideMismatchedBlackjack")
    void mismatchedBlackjackTest(List<Number> playerNumbers, List<Number> dealerNumbers) {
        Player player = ParticipantGenerator.createPlayer(playerNumbers);
        Dealer dealer = ParticipantGenerator.createDealer(dealerNumbers);

        assertThat(playerResultMatcher.isResultMatched(player, dealer))
                .isEqualTo(false);
    }

    private static Stream<Arguments> provideMismatchedBlackjack() {
        return Stream.of(
                Arguments.of(List.of(Number.ACE, Number.FIVE, Number.FIVE), List.of(Number.QUEEN, Number.ACE)),
                Arguments.of(List.of(Number.KING, Number.ACE), List.of(Number.FIVE, Number.SIX, Number.KING))
        );
    }

    @DisplayName("점수와 Blackjack의 상태가 같을 경우 참을 반환한다.")
    @ParameterizedTest
    @MethodSource("provideSameBlackjackAndScore")
    void sameBlackjackAndScoreTest(List<Number> playerNumbers, List<Number> dealerNumbers) {
        Player player = ParticipantGenerator.createPlayer(playerNumbers);
        Dealer dealer = ParticipantGenerator.createDealer(dealerNumbers);

        assertThat(playerResultMatcher.isResultMatched(player, dealer))
                .isEqualTo(true);
    }

    private static Stream<Arguments> provideSameBlackjackAndScore() {
        return Stream.of(
                Arguments.of(List.of(Number.ACE, Number.FIVE, Number.FIVE), List.of(Number.FIVE, Number.SIX, Number.JACK)),
                Arguments.of(List.of(Number.TWO, Number.TWO, Number.FOUR), List.of(Number.THREE, Number.FIVE))
        );
    }

    @DisplayName("점수가 다를 경우 거짓을 반환한다.")
    @ParameterizedTest
    @MethodSource("provideDifferentScore")
    void differentScoreTest(List<Number> playerNumbers, List<Number> dealerNumbers) {
        Player player = ParticipantGenerator.createPlayer(playerNumbers);
        Dealer dealer = ParticipantGenerator.createDealer(dealerNumbers);

        assertThat(playerResultMatcher.isResultMatched(player, dealer))
                .isEqualTo(false);
    }

    private static Stream<Arguments> provideDifferentScore() {
        return Stream.of(
                Arguments.of(List.of(Number.TWO, Number.THREE), List.of(Number.FIVE, Number.SIX, Number.JACK)),
                Arguments.of(List.of(Number.TWO, Number.FIVE), List.of(Number.THREE, Number.EIGHT))
        );
    }
}
