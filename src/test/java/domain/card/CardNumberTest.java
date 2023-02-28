package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardNumberTest {

    @Test
    @DisplayName("getAll()은 호출하면 모든 CardNumber을 반환한다")
    void getAll_whenCall_thenReturnCardNumbers() {
        // given
        List<CardNumber> expected = Arrays.asList(CardNumber.values());

        // when
        List<CardNumber> actual = CardNumber.getAll();

        // then
        assertThat(actual.size()).isSameAs(expected.size());
        assertThat(actual).isEqualTo(expected);
    }
}
