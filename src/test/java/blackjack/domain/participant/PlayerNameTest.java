package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerNameTest {
    @DisplayName("공백으로 이름을 생성하려고 하면 예외를 발생한다..")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    @NullSource
    void invalidName(String input) {
        assertThatThrownBy(() -> new ParticipantName(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참여자 이름에 공백을 입력할 수 없습니다.");
    }

    @DisplayName("공백이 아니라면 이름을 정상적으로 생성한다.")
    @Test
    void validName() {
        assertThat(new ParticipantName("kirby")).isEqualTo(new ParticipantName("kirby"));
    }
}
