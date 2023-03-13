package blackjack.domain;

import helper.StubCardPicker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void createInstance() {
        Participant p1 = new Player("무민");
        Assertions.assertThat(p1).isInstanceOf(Player.class);
    }

    @Test
    void testHit() {
        Participant p1 = new Player("무민");
        Card card = new Card(Pattern.CLOVER, Denomination.FOUR);
        Assertions.assertThat(p1.getCardDeck().getCards().size()).isEqualTo(0);

        p1.hit(new StubCardPicker(card));

        Assertions.assertThat(p1.getCardDeck().getCards().size()).isEqualTo(1);
        Assertions.assertThat(p1.getCardDeck().getCards().contains(card)).isTrue();
    }
}