package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardPatternTest {

    @Test
    @DisplayName("getAll()은 호출하면 모든 CardPattern을 반환한다")
    void getAll_whenCall_thenReturnCardPatterns() {
        // given
        List<CardPattern> expected = Arrays.asList(CardPattern.values());

        // when
        List<CardPattern> actual = CardPattern.getAll();

        // then
        assertThat(actual.size()).isSameAs(expected.size());
        assertThat(actual).isEqualTo(expected);
    }
}
