package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TypeTest {

    @Test
    @DisplayName("타입은 다이아몬드, 클로버, 하트, 스페이스가 있다")
    void generateType() {
        assertThat(Type.values())
            .contains(Type.DIAMOND, Type.CLOVER, Type.HEART, Type.SPACE);
    }
}
