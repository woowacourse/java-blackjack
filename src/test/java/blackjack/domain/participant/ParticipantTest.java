package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Bet;
import blackjack.exception.ErrorException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParticipantTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("이름이 공백인 참여자 예외 테스트")
    void blankParticipantNameTest(String name) {
        // given & when & then
        assertThatThrownBy(() -> new Player(name, Bet.startingBet()))
                .isInstanceOf(ErrorException.class)
                .hasMessageContaining("[ERROR]");
    }
}
