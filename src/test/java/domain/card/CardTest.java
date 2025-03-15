package domain.card;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    void 하나의_카드를_문자열로_변환한다() {
        //given
        Card card = new Card(Pattern.SPADE, CardNumber.TWO);

        //when
        String actual = card.formatSingleCard();

        //then
        assertThat(actual).isEqualTo("2스페이드");
    }

    private static Stream<Arguments> provideHonorCard() {
        return Stream.of(
                Arguments.of(new Card(Pattern.SPADE, CardNumber.ACE), "A스페이드"),
                Arguments.of(new Card(Pattern.CLOVER, CardNumber.JACK), "J클로버"),
                Arguments.of(new Card(Pattern.HEART, CardNumber.QUEEN), "Q하트"),
                Arguments.of(new Card(Pattern.DIAMOND, CardNumber.KING), "K다이아몬드")
        );
    }

    @ParameterizedTest
    @MethodSource("provideHonorCard")
    void honor_card일_경우_카드_숫자는_지정된_문자로_변환해야_한다(Card card, String expected) {
        //when
        String actual = card.formatSingleCard();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
