package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.exception.InvalidHitCommandException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerActionTest {
    @DisplayName("유효하지 않은 명령어가 입력되면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"Y", "N", " ", "yn", "mason"})
    void invalidHitCommand(String command) {
        //given & when & then
        assertThatThrownBy(() -> PlayerAction.getAction(command))
                .isInstanceOf(InvalidHitCommandException.class);
    }

    @DisplayName("명령어 'y'에 해당하는 PlayerAction을 반환한다.")
    @Test
    void getActionYes() {
        //given
        String command = "y";

        //when & then
        assertThat(PlayerAction.getAction(command))
                .isEqualTo(PlayerAction.HIT);
    }

    @DisplayName("명령어 'n'에 해당하는 PlayerAction을 반환한다.")
    @Test
    void getActionNo() {
        //given
        String command = "n";

        //when & then
        assertThat(PlayerAction.getAction(command))
                .isEqualTo(PlayerAction.STAND);
    }
}
