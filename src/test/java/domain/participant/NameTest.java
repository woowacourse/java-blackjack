package domain.participant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    @DisplayName("플레이어 이름은 1글자 이상 8글자 이하여야 한다.")
    void 이름은_1글자_이상_8글자_이하_성공() {
        // given
        String name = "pobi";

        // when - then
        Assertions.assertDoesNotThrow(() -> new Name(name));
    }

    @Test
    @DisplayName("플레이어 이름은 공백을 허용하지 않는다.")
    void 이름_공백_실패() {
        // given
        String name = "";

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Name(name));
    }

    @Test
    @DisplayName("플레이어 이름은 8글자 초과를 허용하지 않는다.")
    void 이름_8글자_초과_실패() {
        // given
        String name = "pobipobip";

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Name(name));
    }
}
