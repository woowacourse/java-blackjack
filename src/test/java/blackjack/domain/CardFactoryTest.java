package blackjack.domain;

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
}
