package balckjack.domain;

import helper.StubCardPicker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardPoolTest {
    @BeforeEach
    void init(){
        CardPool.init();
    }

    @Test
    void createInstance() {
        Assertions.assertThat(CardPool.getSize()).isEqualTo(52);
    }

    @Test
    void drawAceCard() {
        final AceCard target = new AceCard(Pattern.SPADE);
        Assertions.assertThat(CardPool.getCards().contains(target)).isTrue();
        Card card = CardPool.draw(new StubCardPicker(target));

        Assertions.assertThat(CardPool.getCards().contains(card)).isFalse();
        Assertions.assertThat(card).isInstanceOf(AceCard.class);
        Assertions.assertThat(CardPool.getSize()).isEqualTo(51);
    }
}