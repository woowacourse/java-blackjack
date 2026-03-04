package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    Card card = new Card();

    @Test
    void ACE인지_확인한다() {
        Card card = new Card();

        boolean ace = card.isAce();

        assertThat(ace).isEqualTo(Number.ACE);
    }

}
