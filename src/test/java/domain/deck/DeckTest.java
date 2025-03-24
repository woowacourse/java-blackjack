package domain.deck;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    public class FakeShuffleStrategy implements ShuffleStrategy {
        private final List<Card> fixedOrder;

        public FakeShuffleStrategy(final List<Card> fixedOrder) {
            this.fixedOrder = new ArrayList<>(fixedOrder);
        }

        @Override
        public List<Card> shuffle(final List<Card> cards) {
            return new ArrayList<>(fixedOrder);
        }
    }

    @DisplayName("덱을 생성한다.")
    @Test
    void 덱을_생성한다() {

        // given
        // when & then
        assertThatCode(() -> Deck.createShuffledDeck(new RandomShuffleStrategy()))
                .doesNotThrowAnyException();
    }

    @DisplayName("덱에서 카드를 1장 뽑는다.")
    @Test
    void 덱에서_카드를_1장_뽑는다() {

        // given
        final Card card1 = new Card(Rank.ACE, Shape.CLOVER);
        final Card card2 = new Card(Rank.KING, Shape.CLOVER);
        final Deck deck = Deck.createDeck(List.of(card1, card2));

        // when
        final Card drownCard1 = deck.drawCard();
        final Card drownCard2 = deck.drawCard();

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(drownCard1).isEqualTo(card1);
            softly.assertThat(drownCard2).isEqualTo(card2);
        });
    }

    @DisplayName("카드가 부족하면 예외가 발생한다.")
    @Test
    void 카드가_부족하면_예외가_발생한다() {

        // given
        final Deck deck = Deck.createDeck(List.of());

        // when & then
        assertThatThrownBy(deck::drawCard)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드를 셔플한다.")
    @Test
    void 카드를_셔플한다() {

        // given
        final Card card3 = new Card(Rank.KING, Shape.CLOVER);
        final Card card2 = new Card(Rank.FIVE, Shape.CLOVER);
        final Card card1 = new Card(Rank.ACE, Shape.CLOVER);
        final Deck shuffledDeck = Deck.createShuffledDeck(new FakeShuffleStrategy(List.of(card3, card2, card1)));

        // when
        final Card firstCard = shuffledDeck.drawCard();
        final Card secondCard = shuffledDeck.drawCard();
        final Card thirdCard = shuffledDeck.drawCard();

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(firstCard).isEqualTo(card3);
            softly.assertThat(secondCard).isEqualTo(card2);
            softly.assertThat(thirdCard).isEqualTo(card1);
        });
    }
}
