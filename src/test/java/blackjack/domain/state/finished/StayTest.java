package blackjack.domain.state.finished;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.winning.WinningResult;
import org.junit.jupiter.api.Test;

class StayTest {

    @Test
    void 수익은_배팅한_금액만큼_받는다() {
        //given
        Cards dealerCards = new Cards(
                new Card(Suit.DIAMOND, Rank.NINE)
        );
        Stay stay = new Stay(new Cards(
                new Card(Suit.DIAMOND, Rank.TEN)
        ));
        double bettingMoney = 1000;

        //when
        double profit = stay.profit(dealerCards, bettingMoney);

        //then
        assertThat(profit).isEqualTo(1000);
    }

    @Test
    void 상대가_버스트라면_승리이다() {
        //given
        Cards cards = new Cards(
                new Card(Suit.DIAMOND, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.TEN)
        );
        Stay stay = new Stay(new Cards());

        //when
        WinningResult winningResult = stay.decide(cards);

        //then
        assertThat(winningResult).isEqualTo(WinningResult.WIN);
    }

    @Test
    void 상대가_블랙잭이면_패배이다() {
        //given
        Cards cards = new Cards(
                new Card(Suit.HEART, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.TEN)
        );
        Stay stay = new Stay(new Cards());

        //when
        WinningResult winningResult = stay.decide(cards);

        //then
        assertThat(winningResult).isEqualTo(WinningResult.LOSE);
    }

    @Test
    void 같은_Stay라면_점수가_높은_사람이_이긴다() {
        //given
        Cards cards = new Cards(
                new Card(Suit.DIAMOND, Rank.NINE)
        );
        Stay stay = new Stay(new Cards(
                new Card(Suit.DIAMOND, Rank.TEN)
        ));

        //when
        WinningResult winningResult = stay.decide(cards);

        //then
        assertThat(winningResult).isEqualTo(WinningResult.WIN);
    }

    @Test
    void 같은_Stay라면_점수가_낮은_사람이_진다() {
        //given
        Cards cards = new Cards(
                new Card(Suit.DIAMOND, Rank.TEN)
        );
        Stay stay = new Stay(new Cards(
                new Card(Suit.DIAMOND, Rank.NINE)
        ));

        //when
        WinningResult winningResult = stay.decide(cards);

        //then
        assertThat(winningResult).isEqualTo(WinningResult.LOSE);
    }

    @Test
    void 같은_Stay일때_점수가_같다면_무승부이다() {
        //given
        Cards cards = new Cards(
                new Card(Suit.DIAMOND, Rank.TEN)
        );
        Stay stay = new Stay(new Cards(
                new Card(Suit.DIAMOND, Rank.TEN)
        ));

        //when
        WinningResult winningResult = stay.decide(cards);

        //then
        assertThat(winningResult).isEqualTo(WinningResult.DRAW);
    }
}

