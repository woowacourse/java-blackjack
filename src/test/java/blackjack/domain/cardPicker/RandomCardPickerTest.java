package blackjack.domain.cardPicker;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomCardPickerTest {

    @Test
    @DisplayName("범위에 맞는 랜덤한 정수를 가져온다.")
    void pickIndexTest() {
        // given
        CardPicker cardPicker = new RandomCardPicker();
        int size = 10;

        // when
        int actual = cardPicker.pickIndex(size);

        // then
        Assertions.assertThat(actual)
                .isGreaterThanOrEqualTo(0)
                .isLessThan(size);
    }
}
