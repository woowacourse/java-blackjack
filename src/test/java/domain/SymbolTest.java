package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SymbolTest {

    @DisplayName("하트, 스페이드, 다이아몬드, 클로버가 존재한다.")
    @Test
    void 하트_스페이드_다이아몬드_클로버_존재() {
        assertThat(Symbol.values().length).isEqualTo(4);
    }
}
