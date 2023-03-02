package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardTest {

    @Test
    void 문양_이름을_확인한다() {
        final Suit suit = Suit.SPADE;
        final Number number = Number.ACE;

        final Card card = new Card(number, suit);

        assertThat(card.getSuitName()).isEqualTo(suit.getName());
    }

    @Test
    void 숫자_이름을_확인한다() {
        final Suit suit = Suit.SPADE;
        final Number number = Number.ACE;

        final Card card = new Card(number, suit);

        assertThat(card.getNumberName()).isEqualTo(number.getName());
    }

    @Test
    void 숫자_점수를_확인한다() {
        final Suit suit = Suit.SPADE;
        final Number number = Number.ACE;

        final Card card = new Card(number, suit);

        assertThat(card.getScore()).isEqualTo(number.getScore());
    }
}
