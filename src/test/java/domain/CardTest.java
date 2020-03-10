package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    @Test
    void create() {
        assertThat(Card.of("스페이드", "A")).isInstanceOf(Card.class);
    }

    @Test
    void isAce() {
        assertThat(Card.of("스페이드", "A").isAce()).isTrue();
    }
}
