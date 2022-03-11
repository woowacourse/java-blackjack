package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @DisplayName("52개의 생성된 카드 개수가 올바른지 확인한다.")
    @Test
    void total_card_cache() {
        List<Card> cards = Card.getTotalCard();

        assertThat(cards.size()).isEqualTo(52);
    }

}
