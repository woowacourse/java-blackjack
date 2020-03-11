package factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardFactoryTest {
    @Test
    @DisplayName("CardFactory 기능 확인")
    void create() {
        assertThat(CardFactory.create()).isInstanceOf(List.class);
        assertThat(CardFactory.create().size()).isEqualTo(52);
    }
}
