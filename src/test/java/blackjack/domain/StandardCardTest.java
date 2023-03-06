package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StandardCardTest {

    @Test
    void createInstance() {
        StandardCard standardCard = new StandardCard(Pattern.SPADE, "2");
        Assertions.assertThat(standardCard).isInstanceOf(StandardCard.class);
    }
}