package blackjack.domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerNameTest {

    @ParameterizedTest
    @ValueSource(strings = {"아코", "beaver", "애쉬1"})
    @DisplayName("올바른 이름일 때 생성 성공")
    void createSuccess(String name) {
        assertDoesNotThrow(() -> new ParticipantName(name));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"!", " ", "", "   "})
    @DisplayName("잘못된 이름일 때 생성 실패")
    void createFalse(String name) {
        assertThatThrownBy(() -> new ParticipantName(name))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
