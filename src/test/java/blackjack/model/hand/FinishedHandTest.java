package blackjack.model.hand;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class FinishedHandTest {

    private static final Collection<Card> DEFAULT_FINISHED_CARDS = List.of(
            new Card(Rank.JACK, Suit.HEART),
            new Card(Rank.QUEEN, Suit.HEART),
            new Card(Rank.KING, Suit.HEART)
    );
    private static final Card DEFAULT_NEW_CARD = new Card(Rank.TWO, Suit.HEART);

    @Test
    void 힛할_수_없는_상태라고_판단한다() {
        // given
        FinishedHand hand = new BustHand(DEFAULT_FINISHED_CARDS);

        // when
        boolean canHit = hand.canHit();

        // then
        assertThat(canHit).isFalse();
    }

    @Test
    void 힛을_하려고_하면_예외를_던진다() {
        // given
        FinishedHand hand = new BustHand(DEFAULT_FINISHED_CARDS);

        // when & then
        assertThatThrownBy(() -> hand.hit(DEFAULT_NEW_CARD))
                .isInstanceOf(IllegalStateException.class);
    }
}
