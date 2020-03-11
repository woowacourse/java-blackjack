package factory;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardFactoryTest {
    @Test
    void create() {
        assertThat(CardFactory.create().size()).isEqualTo(52);
    }
}
