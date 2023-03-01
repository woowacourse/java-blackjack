package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class CardsTest {
    @Test
    @DisplayName("카드개수가 48개가 아니라면 예외처리한다.")
    void cards_init_validate_test() {
        // given & when
        List<Card> cards = CardFactory.of();
        cards.remove(0);

        // then
        Assertions.assertThatThrownBy(() -> Cards.init(cards)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("카드를 Cards에 저장할 수 있다.")
    void cards_init_test() {
        // given & when
        List<Card> cards = CardFactory.of();

        // then
        Assertions.assertThatNoException().isThrownBy(() -> Cards.init(cards));

    }
}
