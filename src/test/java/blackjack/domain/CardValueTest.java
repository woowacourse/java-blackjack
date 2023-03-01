package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardValueTest {

    @Test
    @DisplayName("makeCards()는 카드를 생성하여 카드 리스트를 반환해준다.")
    void make_card_test() {
        // then
        Assertions.assertThat(CardValue.makeCards()).hasSize(48);
    }

}
