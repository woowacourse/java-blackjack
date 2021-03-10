package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("cards의 next() 호출 시 다른 카드가 나오는 지 테스트")
    void next() {
        Cards cards = Cards.createNormalCards();
        Assertions.assertThat(cards.next()).isNotEqualTo(cards.next());
    }
}