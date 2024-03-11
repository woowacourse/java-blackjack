package model.participant;

import static org.junit.jupiter.api.Assertions.assertThrows;

import model.participant.Names;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NamesTest {
    @Test
    @DisplayName("중복된 플레이어의 이름이 포함된 목록은 예외를 발생한다.")
    void throwExceptionWhenDuplicatedNameInput() {

        List<String> value = List.of("도비", "도비", "프람");

        assertThrows(IllegalArgumentException.class, () -> new Names(value));
    }

    @ParameterizedTest
    @DisplayName("'딜러'가 포함된 문자열을 이용한 생성은 예외를 발생한다.")
    @ValueSource(strings = {"딜러", "딜러도비", "딜러꼬붕", "진짜딜러"})
    void validate_ShouldThrowException_WhenInputStringContainsDealerName(String value) {
        List<String> inputString = List.of(value);

        assertThrows(IllegalArgumentException.class, () -> new Names(inputString));
    }
}

