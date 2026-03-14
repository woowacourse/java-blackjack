package blackjack.model.state;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.Hand;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.util.List;
import org.junit.jupiter.api.Test;

class BustTest {

    private static final List<Card> DEFAULT_BUST_CARDS = List.of(new Card(Rank.KING, Suit.HEART),
            new Card(Rank.QUEEN, Suit.HEART),
            new Card(Rank.JACK, Suit.HEART)
    );

    @Test
    void 생성_시_버스트가_아니라면_예외를_던진다() {
        Hand notBustHand = new Hand();

        assertThatThrownBy(() -> new Bust(notBustHand))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 현재_상태에_대한_수익률을_제공한다() {
        // given
        Hand bustHand = new Hand(DEFAULT_BUST_CARDS);
        Bust bust = new Bust(bustHand);

        // when
        double earningRate = bust.getEarningRate();

        // then
        assertThat(earningRate).isEqualTo(1);
    }
}
