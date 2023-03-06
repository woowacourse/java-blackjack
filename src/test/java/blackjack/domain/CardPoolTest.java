package blackjack.domain;

import helper.StubCardPicker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CardPoolTest {

    private final CardPool cardPool = new CardPool();

    @Test
    void createInstance() {
        Assertions.assertThat(cardPool.getSize()).isEqualTo(52);
    }

    @Test
    void drawAceCard() {
        final AceCard target = new AceCard(Pattern.SPADE);
        Assertions.assertThat(cardPool.draw(new StubCardPicker(target))).isEqualTo(target);
        Assertions.assertThat(cardPool.getSize()).isEqualTo(51);
    }
}