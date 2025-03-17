package blackjack.domain.state.finished;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.winning.WinningResult;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    void 수익은_배팅금액의_1점5배만큼_받는다() {
        //given
        Cards dealerCards = new Cards(new Card(Suit.DIAMOND, Rank.TEN));
        double bettingMoney = 1000;
        Blackjack blackjack = new Blackjack(new Cards(
                new Card(Suit.DIAMOND, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.ACE)
        ));

        //when
        double profit = blackjack.profit(dealerCards, bettingMoney);

        //then
        assertThat(profit).isEqualTo(1500);
    }

    @Test
    void 상대방도_블랙잭이라면_무승부이다() {
        //given
        Cards cards = new Cards(
                new Card(Suit.DIAMOND, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.ACE)
        );
        Blackjack blackjack = new Blackjack(new Cards(
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.HEART, Rank.ACE)
        ));

        //when
        WinningResult winningResult = blackjack.decide(cards);

        //then
        assertThat(winningResult).isEqualTo(WinningResult.DRAW);
    }

    @Test
    void 상대방이_블랙잭이_아니라면_무조건_이긴다() {
        //given
        Blackjack blackjack = new Blackjack(new Cards(
                new Card(Suit.DIAMOND, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.ACE)
        ));
        Cards cards = new Cards(
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.TEN)
        );

        //when
        WinningResult winningResult = blackjack.decide(cards);

        //then
        assertThat(winningResult).isEqualTo(WinningResult.WIN);
    }
}
