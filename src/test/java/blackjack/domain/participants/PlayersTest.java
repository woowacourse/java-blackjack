package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayersTest {

    @Nested
    @DisplayName("Players를 생성할 때")
    class PlayerInitiatorTest {

        @Test
        void 플레이어의_목록이_null_이면_예외() {
            assertThatThrownBy(() -> Players.from(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("사용자 이름이 입력되지 않았습니다");
        }

        @Test
        void 플레이어의_수가_0명이면_예외() {
            final List<String> playerNames = new ArrayList<>();

            assertThatThrownBy(() -> Players.from(playerNames))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("사용자 수는 1 이상 6 이하여야 합니다. 현재 : 0 명입니다");
        }

        @Test
        void 플레이어의_수가_6명초과면_예외() {
            final List<String> playerNames = List.of("pobi", "crong", "honux", "wannte", "디디", "누누", "이레");

            assertThatThrownBy(() -> Players.from(playerNames))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("사용자 수는 1 이상 6 이하여야 합니다. 현재 : 7 명입니다");
        }

        @Test
        void 플레이어의_이름이_중복되면_예외() {
            final List<String> playerNames = List.of("pobi", "pobi", "honux", "wannte", "디디");

            assertThatThrownBy(() -> Players.from(playerNames))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("사용자의 이름이 중복됩니다.");
        }
    }


}
