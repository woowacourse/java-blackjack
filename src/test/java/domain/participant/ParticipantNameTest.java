package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParticipantNameTest {
    @DisplayName("이름은 null을 허용하지 않는다.")
    @Test
    void createNameFailTestByNull() {
        assertThatThrownBy(() -> new ParticipantName(null))
                .isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest(name = "이름의 길이는 1글자 이상 5글자 이하여야 합니다.")
    @ValueSource(strings = {"", "123456"})
    void createNameFailTestByLength(String name) {
        assertThatThrownBy(() -> new ParticipantName(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
