package blackjack.domain.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PlayersTest {
    @Test
    @DisplayName("입력받은 플레이어의 수가 1명 미만이면 예외가 발생한다.")
    void createZeroPlayer() {
        // given
        List<String> names = List.of();

        // expect
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Players(names))
                .withMessage("[ERROR] 참여할 사람은 한 명 이상이어야 합니다.");
    }

    @Nested
    @DisplayName("입력받은 플레이어 이름에")
    class whenInputPlayersName {
        @Test
        @DisplayName("중복된 이름이 없으면 예외가 발생하지 않는다.")
        void createParticipants_success() {
            // given
            List<String> namesSuccess = List.of("encho", "glen");

            // expect
            assertThatNoException()
                    .isThrownBy(() -> new Players(namesSuccess));
        }

        @Test
        @DisplayName("중복된 이름이 있으면 예외가 발생한다")
        void createParticipants_fail() {
            // given
            List<String> namesFail = List.of("encho", "encho");

            // expect
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Players(namesFail))
                    .withMessage("[ERROR] 중복된 이름이 있습니다.");
        }
    }
}