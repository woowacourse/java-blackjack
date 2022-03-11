package blackJack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @Test
    @DisplayName("카드덱 생성 테스트")
    void createValidDeck() {
        assertThat(new Deck()).isNotNull();
    }
}
