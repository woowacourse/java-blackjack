package blackjack.domain.state.finished;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.winning.WinningResult;
import org.junit.jupiter.api.Test;

class BustTest {

    @Test
    void 수익은_배팅한_금액만큼_잃는다() {
        //given
        Cards dealerCards = new Cards(
                new Card(Suit.DIAMOND, Rank.NINE)
        );
        double bettingMoney = 1000;
        Bust bust = new Bust(new Cards(
                new Card(Suit.DIAMOND, Rank.THREE),
                new Card(Suit.DIAMOND, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.TEN)
        ));

        //when
        double profit = bust.profit(dealerCards, bettingMoney);

        //then
        assertThat(profit).isEqualTo(-1000);
    }

    @Test
    void 버스트라면_무조건_진다() {
        //given
        Cards cards = new Cards(new Card(Suit.DIAMOND, Rank.TEN));
        Bust bust = new Bust(new Cards(
                new Card(Suit.DIAMOND, Rank.THREE),
                new Card(Suit.DIAMOND, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.TEN)
        ));

        //when
        WinningResult winningResult = bust.decide(cards);

        //then
        assertThat(winningResult).isEqualTo(WinningResult.LOSE);
    }
}
