package balckjack.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AceCardTest {

    @Test
    void createInstance() {
        Card card = new AceCard();
        Assertions.assertThat(card).isInstanceOf(AceCard.class);
    }

    @Test
    void getValue() {
        Card aceCard = new AceCard();
        Assertions.assertThat(aceCard.getValue()).isEqualTo(11);
    }
}