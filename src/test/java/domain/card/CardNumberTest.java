package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest(name = "isAce()는 호출하면, 에이스인지 여부를 반환한다")
    @CsvSource(value = {"ACE:true", "TWO:false", "KING:false"}, delimiter = ':')
    void isAce_whenCall_thenReturnIsAce(final CardNumber cardNumber, final boolean expected) {
        boolean actual = cardNumber.isAce();

        assertThat(actual).isSameAs(expected);
    }
}
