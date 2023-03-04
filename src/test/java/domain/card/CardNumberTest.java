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
    @DisplayName("findTotalCardNumber()은 호출하면 모든 CardNumber을 반환한다")
    void findTotalCardNumber_whenCall_thenReturnCardNumbers() {
        // given
        final List<CardNumber> expected = Arrays.asList(CardNumber.values());

        // when
        final List<CardNumber> actual = CardNumber.findTotalCardNumber();

        // then
        assertThat(actual.size())
                .isSameAs(expected.size());

        assertThat(actual)
                .isEqualTo(expected);
    }

    @ParameterizedTest(name = "checkAce()는 호출하면, 에이스인지 여부를 반환한다")
    @CsvSource(value = {"ACE:true", "TWO:false", "KING:false"}, delimiter = ':')
    void checkAce_whenCall_thenReturnIsAce(final CardNumber cardNumber, final boolean expected) {
        final boolean actual = cardNumber.checkAce();

        assertThat(actual)
                .isSameAs(expected);
    }
}
