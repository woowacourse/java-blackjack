package domain.people;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InvalidNamesTest {

    @ParameterizedTest(name = "사용할 수 없는 이름일 때 참값을 반환한다.")
    @ValueSource(strings = {"딜러", "심한 욕"})
    void isInvalidNamesTest(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            Player player = new Player(name);
        });
    }
}
