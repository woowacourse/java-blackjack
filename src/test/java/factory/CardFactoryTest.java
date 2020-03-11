package factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardFactoryTest {
    @Test
    @DisplayName("CardFactory 생성")
    void create() {
        assertThat(CardFactory.create().size()).isEqualTo(52);
    }
}
