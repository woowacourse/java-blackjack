package blackjack.model.state;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Hand;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayingTest {

    private static final Collection<Card> DEFAULT_PLAYING_CARDS = List.of();

    @Test
    void 힛할_수_있는_상태라고_판단한다() {
        // given
        Hand hand = new Hand(DEFAULT_PLAYING_CARDS);
        Playing playing = new Hit(hand);

        // when
        boolean canHit = playing.canHit();

        // then
        assertThat(canHit).isTrue();
    }
}
