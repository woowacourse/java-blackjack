package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StandardCardTest {

    @Test
    void createInstance() {
        StandardCard standardCard = new StandardCard(Pattern.SPADE, "2");
        Assertions.assertThat(standardCard).isInstanceOf(StandardCard.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "11", "0", "12"})
    void valueIsInvalid(String input) {
        Assertions.assertThatThrownBy(() -> new StandardCard(Pattern.SPADE, input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("심볼은 2 ~ 10 사이 숫자여야 합니다. 입력된 값 : %d", Integer.parseInt(input)));
    }

    @Test
    void getCardValueAndPattern() {
        Pattern pattern = Pattern.SPADE;
        String value = "2";
        StandardCard standardCard = new StandardCard(pattern, value);

        Assertions.assertThat(standardCard.getCardValueAndPattern()).isEqualTo(value + pattern.getName());
    }
}
