package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    void ACE인지_확인한다() {
        Card card = new Card(Rank.ACE, Suit.CLOVER);

        boolean ace = card.isAce();

        assertThat(ace).isEqualTo(true);
    }
}
