package blackJack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("카드덱 생성 테스트")
    void createValidDeck() {
        assertThat(new Deck()).isNotNull();
    }
}
