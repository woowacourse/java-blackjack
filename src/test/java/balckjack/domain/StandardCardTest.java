package balckjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StandardCardTest {

    @Test
    void createInstance() {
        StandardCard standardCard = new StandardCard("2");
        Assertions.assertThat(standardCard).isInstanceOf(StandardCard.class);
    }

    @Test
    void getValue() {
        Card standardCard = new StandardCard("3");
        Assertions.assertThat(standardCard.getValue()).isEqualTo(3);
    }

}