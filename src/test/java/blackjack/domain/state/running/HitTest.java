package blackjack.domain.state.running;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.finished.Stay;
import org.junit.jupiter.api.Test;

class HitTest {

    @Test
    void 카드를_뽑을_수_있다() {
        //given
        Hit hit = new Hit(new Cards(new Card(Suit.DIAMOND, Rank.TEN)));

        //when
        State newState = hit.draw(new Card(Suit.DIAMOND, Rank.ONE));

        //then
        assertThat(newState).isEqualTo(new Hit(
                new Cards(
                        new Card(Suit.DIAMOND, Rank.TEN),
                        new Card(Suit.DIAMOND, Rank.ONE)
                )
        ));
    }

    @Test
    void 카드를_뽑을때_버스트라면_버스트가_반환된다() {
        //given
        Hit hit = new Hit(new Cards(
                new Card(Suit.DIAMOND, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.NINE))
        );

        //when
        State newState = hit.draw(new Card(Suit.DIAMOND, Rank.FOUR));

        //then
        assertThat(newState).isEqualTo(new Bust(
                new Cards(
                        new Card(Suit.DIAMOND, Rank.TEN),
                        new Card(Suit.DIAMOND, Rank.NINE),
                        new Card(Suit.DIAMOND, Rank.FOUR)
                )
        ));
    }

    @Test
    void 스테이를_한다면_스테이가_반환된다() {
        //given
        Hit hit = new Hit(new Cards(
                new Card(Suit.DIAMOND, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.NINE))
        );

        //when
        State stay = hit.stay();

        //then
        assertThat(stay).isEqualTo(new Stay(new Cards(
                new Card(Suit.DIAMOND, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.NINE)
        )));
    }
}
