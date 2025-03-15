package domain.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("덱을 생성한다.")
    @Test
    void 덱을_생성한다() {

        // given
        final CardSetGenerator cardSetGenerator = new CardSetGenerator();

        // when & then
        assertThatCode(() -> new Deck(cardSetGenerator.generate()))
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

    @DisplayName("초기 카드 두 장을 추출한다.")
    @Test
    void 초기_카드_두_장을_추출한다() {

        // given
        final CardSetGenerator cardSetGenerator = new CardSetGenerator();
        final Deck deck = new Deck(cardSetGenerator.generate());

        // when
        List<Card> initialCards = deck.getInitialGameCards();

        // when & then
        assertThat(initialCards.size()).isEqualTo(2);
    }

    @DisplayName("카드가 부족하면 예외가 발생한다.")
    @Test
    void 카드가_부족하면_예외가_발생한다() {

        // given
        final Deck deck = new Deck(List.of());

        // when & then
        assertThatThrownBy(deck::drawCard)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
