package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckFactoryTest {
    @Test
    @DisplayName("Deck 생성")
    void create() {
        assertThat(DeckFactory.create()).isNotNull();
    }
}
