package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class AceCardTest {

    @Test
    void 에이스_카드를_만든다() {
        // given
        AceCard card = new AceCard();

        // then
        assertThat(card).isNotNull();
    }
}
