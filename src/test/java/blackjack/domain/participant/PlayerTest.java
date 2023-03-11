package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerTest {

    @DisplayName("플레이어는 이름을 가진다.")
    @Test
    void should_NotThrowException_When_CreateWithName() {
        assertThatCode(() -> new Player("이름"))
                .doesNotThrowAnyException();
    }

    @DisplayName("빈 문자열이거나 공백인 이름은 허용하지 않는다.")
    @ParameterizedTest
    @NullAndEmptySource
    void should_ThrowException_When_NameIsEmpty(String name) {
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 빈 문자열이거나 공백일 수 없습니다.");
    }
}
