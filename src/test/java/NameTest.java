import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class NameTest {
    @DisplayName("이름을 생성한다.")
    @Test
    void 이름_생성() {
        assertDoesNotThrow(()->new Name("name"));
    }
}
