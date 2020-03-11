package domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WhetherAddCardTest {
    @Test
    @DisplayName("WhetherAddCard 생성")
    void create() {
        assertThat(WhetherAddCard.of("y")).isInstanceOf(WhetherAddCard.class);
        assertThat(WhetherAddCard.of("n")).isInstanceOf(WhetherAddCard.class);
    }

    @Test
    @DisplayName("y인지 n인지 확인")
    void isYes() {
        assertThat(WhetherAddCard.of("y").isYes()).isTrue();
        assertThat(WhetherAddCard.of("n").isYes()).isFalse();
    }
}
