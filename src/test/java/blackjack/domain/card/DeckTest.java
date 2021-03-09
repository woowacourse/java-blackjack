package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {
    @Test
    @DisplayName("덱의 카드 종류 52가지 확인 테스트")
    void DeckSize() {
        assertThat(Deck.getCards()
                .size()).isEqualTo(52);
    }
}
