package balckjack.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CardPoolTest {

    @Test
    void createInstance() {
        Assertions.assertThat(CardPool.getSize()).isEqualTo(52);
    }

}