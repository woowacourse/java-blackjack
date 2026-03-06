package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CardDispenserTest {

    @Test
    void 섞인_카드로_디스펜서가_카드를_n장_나누어주면_카드를_받은_플레이어의_카드는_n장이_된다() {
        // given
        Cards cards = Cards.createDeck();
        Player pobi = new Player("pobi");
        CardDispenser dispenser = new CardDispenser(cards);

        // when
        dispenser.dispense(pobi, 2);

        // then
        assertThat(pobi.getCards().size()).isEqualTo(2);
    }
}
