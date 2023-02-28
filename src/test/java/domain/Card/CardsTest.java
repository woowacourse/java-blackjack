package domain.Card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {
    @Test
    @DisplayName("블랙잭 전체 카드를 생성한 직후에는 비어있으면 안된다.")
    void generateCardsTest() {
        //given
        Cards cards = new Cards();

        //when
        boolean result = cards.isEmpty();

        //then
        assertThat(result).isFalse();
    }
}
