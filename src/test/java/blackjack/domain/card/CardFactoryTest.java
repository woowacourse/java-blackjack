package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CardFactoryTest {
    @Test
    @DisplayName("of()는 48개의 카드를 생성하여 반환한다.")
    void test_() {
        // given & when
        List<Card> cards = CardFactory.of();
        int expectedSize = 48;

        // then
        Assertions.assertThat(cards).hasSize(expectedSize);

    }

    // TODO : 예외처리 리팩토링 고민해보기
    @Test
    @DisplayName("카드개수가 48개가 아니라면 예외처리한다.")
    void cards_init_validate_test() {
        // given & when
        List<Card> cards = CardFactory.of();

        // then
        Assertions.assertThatThrownBy(() -> Cards.init())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드의 개수는 총 48개여야 합니다.");
    }
}
