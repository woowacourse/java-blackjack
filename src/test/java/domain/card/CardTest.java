package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Card 은")
class CardTest {

    @Test
    void 카드_값을_갖는다() {
        // given
        final Card card = new Card(CardShape.CLOVER, CardValue.TWO);

        // when
        CardValue cardValue = card.cardValue();

        // then
        assertThat(cardValue.value()).isEqualTo(2);
    }

    @Test
    void 카드_종류를_가진다() {
        // given
        final Card card = new Card(CardShape.CLOVER, CardValue.TEN);

        // when & then
        assertThat(card.cardShape()).isEqualTo(CardShape.CLOVER);
    }

    @Test
    void 모양과_값이_같으면_같은_카드이다() {
        // given
        final Card card1 = new Card(CardShape.CLOVER, CardValue.TWO);
        final Card card2 = new Card(CardShape.CLOVER, CardValue.TWO);

        // when & then
        assertThat(card1).isEqualTo(card2);
    }
}
