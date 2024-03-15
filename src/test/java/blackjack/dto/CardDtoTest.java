package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class CardDtoTest {

    @Test
    void dto로_변환할_수_있다() {
        final Card card = new Card(Suit.HEART, Denomination.ACE);
        final CardDto cardDto = CardDto.from(card);

        assertSoftly(softly -> {
            softly.assertThat(cardDto.suit()).isEqualTo("하트");
            softly.assertThat(cardDto.denomination()).isEqualTo("A");
        });
    }
}
