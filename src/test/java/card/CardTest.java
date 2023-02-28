package card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Card 은")
class CardTest {

    @Test
    void 카드_값을_갖는다() {
        // given
        final Card card = new Card(CardValue.TWO);

        // when
        CardValue cardValue = card.cardValue();

        // then
        assertThat(cardValue.value()).isEqualTo(2);
    }

    @ParameterizedTest(name = "{arguments} 카드를 가질 수 있다.")
    @ValueSource(classes = {DiamondCard.class, HeartCard.class, CloverCard.class, SpadeCard.class})
    void 여러_카드_종류가_있다(final Class<? extends Card> type) throws Exception {
        // given
        final Card card = type.cast(type.getDeclaredConstructor(CardValue.class).newInstance(CardValue.TWO));

        // when & then
        assertThat(card).isInstanceOf(type);
    }
}