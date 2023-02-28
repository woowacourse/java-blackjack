package area;

import card.Card;
import card.CardValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("CardArea 은")
class CardAreaTest {

    @Test
    void 카드를_두장만_받아서_생성된다() {
        // when & then
        assertDoesNotThrow(() -> new CardArea(new Card(CardValue.ACE), new Card(CardValue.TWO)));
    }

    @Test
    void 카드를_추가할_수_있다() {
        // given
        final CardArea cardArea = new CardArea(new Card(CardValue.THREE), new Card(CardValue.TWO));

        // when
        final int beforeSize = cardArea.cards().size();
        cardArea.addCard(new Card(CardValue.FOUR));

        // then
        assertThat(cardArea.cards().size()).isEqualTo(beforeSize + 1);
    }
}