package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TypeTest {

    @Test
    void isAce() {
        Type type = Type.ACE;
        assertThat(type.isAce()).isTrue();
    }
}