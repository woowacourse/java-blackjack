package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

class PlayerRosterTest {

    @Nested
    class InvalidCases {

        @DisplayName("플레이어 이름 목록은 이름들을 가져야 한다.")
        @ParameterizedTest
        @NullSource
        @EmptySource
        void validateNotNull(List<Name> invalidPlayerNames) {
            // when & then
            assertThatThrownBy(() -> new PlayerRoster(invalidPlayerNames))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어 이름 목록은 이름들을 가져야 합니다.");
        }

        @DisplayName("플레이어 이름은 중복될 수 없다.")
        @Test
        void validateNotDuplicate() {
            // given
            List<Name> duplicatePlayerNames = List.of(new Name("머피"), new Name("머피"));

            // when & then
            assertThatThrownBy(() -> new PlayerRoster(duplicatePlayerNames))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
        }
    }
}
