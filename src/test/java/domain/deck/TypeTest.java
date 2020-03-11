package domain.deck;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TypeTest {

    @Test
    @DisplayName("ACE인지 검사")
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