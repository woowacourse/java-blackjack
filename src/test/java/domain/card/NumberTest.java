package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberTest {
    
    @Test
    @DisplayName("해당 넘버가 ACE인지 확인")
    void isAce() {
        assertThat(Number.ACE.isAce()).isTrue();
    }
}