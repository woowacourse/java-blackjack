package domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void create() {
        assertThat(Dealer.appoint()).isNotNull();
    }
}