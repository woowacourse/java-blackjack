package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParticipantTest {

    @ParameterizedTest
    @DisplayName("양쪽 공백을 제외한 이름의 글자 수가 1글자 이상이 아닐 경우 예외 발생")
    @ValueSource(strings = {"   ", " "})
    public void validateParticipantName(String name) {
        assertThatCode(() -> {
            new Participant(name);
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("플레이어 이름은 양쪽 공백을 제외한 1글자 이상이어야 합니다.");
    }
}
