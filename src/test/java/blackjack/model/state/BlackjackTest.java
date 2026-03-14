package blackjack.model.state;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.Hand;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    private static final Collection<Card> DEFAULT_BLACKJACK_CARDS = List.of(
            new Card(Rank.ACE, Suit.HEART),
            new Card(Rank.TEN, Suit.HEART)
    );

    @Test
    void 생성_시_블랙잭이_아니라면_예외를_던진다() {
        Hand notBlackjackHand = new Hand();

        assertThatThrownBy(() -> new Blackjack(notBlackjackHand))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 블랙잭_수익률을_제공한다() {
        // given
        Hand blackjackHand = new Hand(DEFAULT_BLACKJACK_CARDS);
        Blackjack blackjack = new Blackjack(blackjackHand);

        // when
        double earningRate = blackjack.getEarningRate();

        // then
        assertThat(earningRate).isEqualTo(1.5);
    }
}
