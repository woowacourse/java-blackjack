package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameTest {
    @Test
    @DisplayName("Name은 전달된 이름을 그대로 보관한다")
    void keepName() {
        Name name = new Name("pobi");

        assertEquals("pobi", name.getName());
    }
}
