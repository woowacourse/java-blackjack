package domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.user.Dealer;

class DealerTest {

    @Test
    void create() {
        assertThat(new Dealer()).isNotNull();
    }
}