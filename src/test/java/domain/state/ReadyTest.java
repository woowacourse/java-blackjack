package domain.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReadyTest {

    @Test
    void ready() {
        final var state = new Ready();

        assertThat(state).isInstanceOf(Ready.class);
    }
}
