package blackjack.domain;

import static blackjack.domain.Number.QUEEN;
import static blackjack.domain.Number.SEVEN;
import static blackjack.domain.Number.SIX;
import static blackjack.domain.Number.TWO;
import static blackjack.domain.Suit.CLOVER;
import static blackjack.domain.Suit.DIAMOND;
import static blackjack.domain.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {

    @Test
    void 카드를_뽑을_수_있으면_true_반환한다() {
        final Cards cards = new Cards(List.of(
                new Card(QUEEN, CLOVER),
                new Card(SIX, HEART)
        ));
        final Dealer dealer = new Dealer(cards);

        assertThat(dealer.isHittable()).isTrue();
    }

    @Test
    void 카드를_뽑을_수_없으면_false_반환한다() {
        final Cards cards = new Cards(List.of(
                new Card(QUEEN, CLOVER),
                new Card(SEVEN, HEART)
        ));
        final Dealer dealer = new Dealer(cards);

        assertThat(dealer.isHittable()).isFalse();
    }

    @Test
    void 소지한_카드가_3장이라면_false_반환한다() {
        final Cards cards = new Cards(List.of(
                new Card(TWO, CLOVER),
                new Card(SIX, HEART),
                new Card(SEVEN, DIAMOND)
        ));
        final Dealer dealer = new Dealer(cards);

        assertThat(dealer.isHittable()).isFalse();
    }

    @Test
    void 점수를_확인한다() {
        final Cards cards = new Cards(List.of(
                new Card(TWO, CLOVER),
                new Card(SIX, HEART),
                new Card(SEVEN, DIAMOND)
        ));
        final Dealer dealer = new Dealer(cards);

        assertThat(dealer.getScore()).isEqualTo(15);
    }
}

class Dealer {
    
    private static final int MAXIMUM_CARD_COUNT = 2;
    private static final int MAXIMUM_SCORE = 16;

    private final Cards cards;

    public Dealer(final Cards cards) {
        this.cards = cards;
    }

    public boolean isHittable() {
        return isCardShortage() && isScoreLow();
    }

    private boolean isCardShortage() {
        return cards.count() <= MAXIMUM_CARD_COUNT;
    }

    private boolean isScoreLow() {
        return cards.calculateTotalScore() <= MAXIMUM_SCORE;
    }

    public int getScore() {
        return cards.calculateTotalScore();
    }
}
