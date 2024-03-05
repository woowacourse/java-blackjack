import static org.assertj.core.api.Assertions.assertThat;

import domain.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {
    @DisplayName("블랙잭에 필요한 카드들을 생성해둔다.")
    @Test
    void createCards() {
        Cards cards = new Cards();

        assertThat(cards.getTotalSize()).isEqualTo(52);
    }
}
