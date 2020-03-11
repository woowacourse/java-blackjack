package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TypeTest {

    @Test
    void isAce() {
        Type type = Type.ACE;
        assertThat(type.isAce()).isTrue();
    }

    @Test
    void getPoint() {
        Type jack = Type.JACK;
        assertThat(jack.getPoint()).isEqualTo(10);
    }

    @Test
    void getName() {
        Type jack = Type.JACK;
        assertThat(jack.getName()).isEqualTo("J");
    }
}