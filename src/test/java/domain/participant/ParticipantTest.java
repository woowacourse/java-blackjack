package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantTest {

    @DisplayName("본인이 딜러라면 true를 반환한다.")
    @MethodSource("isDealerParameters")
    @ParameterizedTest(name = "[{index}] \"{0}\" => {1}")
    void isDealerTest(Participant participant, boolean expected) {
        // When
        boolean isDealer = participant.isDealer();

        // Then
        assertThat(isDealer).isEqualTo(expected);
    }

    private static Stream<Arguments> isDealerParameters() {
        return Stream.of(
                Arguments.of(Dealer.init(), true),
                Arguments.of(Player.of(new PlayerName("kelly")), false)
        );
    }
}
