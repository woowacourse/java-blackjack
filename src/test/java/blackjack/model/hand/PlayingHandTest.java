package blackjack.model.hand;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.model.card.Card;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayingHandTest {

    private static final Collection<Card> DEFAULT_PLAYING_CARDS = List.of();

    @Test
    void 힛할_수_있는_상태라고_판단한다() {
        // given
        PlayingHand hand = new HitHand(DEFAULT_PLAYING_CARDS);

        // when
        boolean canHit = hand.canHit();

        // then
        assertThat(canHit).isTrue();
    }
}
