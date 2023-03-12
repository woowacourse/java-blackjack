package domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Ready 상태는 ")
class ReadyTest {
    @Test
    @DisplayName("패가 0장인 상태로 생성된다.")
    void createReadyTest() {
        final Ready ready = new Ready();
        assertThat(ready.getCards()).isEmpty();
    }
}
