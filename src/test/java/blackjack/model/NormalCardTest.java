package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class NormalCardTest {

    @Test
    void 일반_카드는_숫자가_있다() {
        // given
        NormalCard card = new NormalCard(2);

        // when
        int number = card.getNumber();

        // then
        assertThat(number).isEqualTo(2);
    }
}
