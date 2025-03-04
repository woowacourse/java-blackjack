package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SpecialCardTest {

    @Test
    void 스페셜_카드는_문자가_있다() {
        // given
        SpecialCard card = new SpecialCard("J");

        // when
        String character = card.getCharacter();

        // then
        assertThat(character).isEqualTo("J");
    }
}
