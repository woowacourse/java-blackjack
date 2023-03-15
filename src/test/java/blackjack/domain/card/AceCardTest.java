package blackjack.domain.card;

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
    void getCardValueAndPattern() {
        Pattern pattern = Pattern.SPADE;
        String value = "A";
        AceCard aceCard = new AceCard(pattern);

        Assertions.assertThat(aceCard.getCardValueAndPattern()).isEqualTo(value + pattern.getName());
    }
}
