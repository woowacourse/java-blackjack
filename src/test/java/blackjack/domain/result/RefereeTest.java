package blackjack.domain.result;

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

class RefereeTest {
    @DisplayName("플레이어의 결과를 올바르게 판단한다.")
    @ParameterizedTest
    @MethodSource("provideParticipantsWithHandResult")
    void generateResultTest(List<Number> playerNumbers, List<Number> dealerNumbers, HandResult expectedResult) {
        Player player = ParticipantGenerator.createPlayer(playerNumbers);
        Dealer dealer = ParticipantGenerator.createDealer(dealerNumbers);
        Referee referee = Referee.getInstance();
        HandResult playerResult = referee.getPlayerResult(player, dealer);

        assertThat(playerResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideParticipantsWithHandResult() {
        return Stream.of(
                Arguments.of(List.of(Number.TWO, Number.SIX), List.of(Number.FOUR, Number.TWO, Number.TWO), HandResult.DRAW),
                Arguments.of(List.of(Number.TWO, Number.EIGHT, Number.SIX), List.of(Number.TWO, Number.FOUR), HandResult.WIN),
                Arguments.of(List.of(Number.SEVEN, Number.TWO), List.of(Number.QUEEN, Number.SEVEN), HandResult.LOSE),
                Arguments.of(List.of(Number.SEVEN, Number.TWO, Number.TWO, Number.JACK), List.of(Number.QUEEN, Number.ACE), HandResult.LOSE),
                Arguments.of(List.of(Number.ACE, Number.JACK), List.of(Number.FIVE, Number.SIX, Number.ACE), HandResult.BLACKJACK_WIN)
        );
    }
}
