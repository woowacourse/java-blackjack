package domain.people;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InvalidNamesTest {

    @Test
    @DisplayName("사용할 수 없는 이름일 때 참값을 반환한다.")
    void isInvalidNamesTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            Player player = new Player("딜러");
        });
    }
}
