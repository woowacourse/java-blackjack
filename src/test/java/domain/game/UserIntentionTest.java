package domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserIntentionTest {
    @Test
    @DisplayName("UserIntention 생성")
    void create() {
        assertThat(UserIntention.of("y")).isInstanceOf(UserIntention.class);
        assertThat(UserIntention.of("n")).isInstanceOf(UserIntention.class);
    }

    @Test
    @DisplayName("y인지 n인지 확인")
    void isYes() {
        assertThat(UserIntention.of("y").isYes()).isTrue();
        assertThat(UserIntention.of("n").isYes()).isFalse();
    }
}
