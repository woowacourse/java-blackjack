package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    void playersTest() {
        assertThatCode(() -> Players.from(List.of("leo", "reo", "reoleo")))
            .doesNotThrowAnyException();
    }
}
