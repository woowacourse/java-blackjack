package blackjack.domain.game;

import blackjack.domain.card.AceCard;
import blackjack.domain.card.Pattern;
import blackjack.domain.game.CardPool;
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