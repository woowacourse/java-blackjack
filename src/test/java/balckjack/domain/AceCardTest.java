package balckjack.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class AceCardTest {

    @ParameterizedTest
    @EnumSource(value = Pattern.class)
    void createInstance(Pattern pattern) {
        Card card = new AceCard(pattern);
        Assertions.assertThat(card).isInstanceOf(AceCard.class);
    }

    @Test
    void getValue() {
        Card aceCard = new AceCard(Pattern.SPADE);
        Assertions.assertThat(aceCard.getValue()).isEqualTo(11);
    }
}