package model.participant;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NamesTest {
    @Test
    @DisplayName("중복된 플레이어의 이름이 포함된 목록은 예외를 발생한다.")
    void throwExceptionWhenDuplicatedNameInput() {

        List<String> value = List.of("도비", "도비", "프람");

        assertThrows(IllegalArgumentException.class, () -> new Names(value));
    }
}
