package blackJack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("카드 덱 성공적으로 생성할 경우 null 값을 반환하지 않는다.")
    void createValidDeck() {
        assertThat(Deck.create()).isNotNull();
    }
}
