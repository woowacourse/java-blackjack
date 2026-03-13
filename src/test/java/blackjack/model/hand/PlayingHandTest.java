package blackjack.model.hand;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayingHandTest {

    static final Collection<Card> DEFAULT_EXIST_CARDS = List.of();
    static final Card DEFAULT_NEW_CARD = new Card(Rank.ACE, Suit.HEART);

    @Test
    void 힛할_수_있는_상태라고_판단한다() {
        // given
        PlayingHand hand = new HitHand(DEFAULT_EXIST_CARDS, DEFAULT_NEW_CARD);

        // when
        boolean canHit = hand.canHit();

        // then
        assertThat(canHit).isTrue();
    }
}
