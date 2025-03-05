package domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("덱을 생성한다.")
    @Test
    void 덱을_생성한다() {

        // given
        final DeckGenerator deckGenerator = new DeckGenerator();

        // when & then
        assertThatCode(() -> new Deck(deckGenerator.generate()))
                .doesNotThrowAnyException();
    }

    @DisplayName("덱에서 카드를 1장 뽑는다.")
    @Test
    void 덱에서_카드를_1장_뽑는다() {

        // given
        final Card card1 = new Card(Rank.ACE, Shape.CLOVER);
        final Card card2 = new Card(Rank.KING, Shape.CLOVER);
        final Deck deck = new Deck(List.of(card1, card2));

        // when
        final Card drownCard1 = deck.drawCard();
        final Card drownCard2 = deck.drawCard();

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(drownCard1).isEqualTo(card1);
            softly.assertThat(drownCard2).isEqualTo(card2);
        });
    }
}
