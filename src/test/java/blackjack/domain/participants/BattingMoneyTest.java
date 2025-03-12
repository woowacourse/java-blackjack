package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.winning.WinningResult;
import org.junit.jupiter.api.Test;

class BattingMoneyTest {

    @Test
    void 버스트라면_배팅금액만큼_잃는다() {
        //given
        Cards cards = new Cards(
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.TWO)
        );
        BattingMoney battingMoney = new BattingMoney(1000);

        //when
        int revenue = battingMoney.calculateRevenue(cards, WinningResult.LOSE);

        //then
        assertThat(revenue).isEqualTo(-1000);
    }

    @Test
    void 블랙잭으로_승리한경우_1점5배의_수익을_얻는다() {
        //given
        Cards cards = new Cards(
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.ACE)
        );
        BattingMoney battingMoney = new BattingMoney(1000);

        //when
        int revenue = battingMoney.calculateRevenue(cards, WinningResult.WIN);

        //then
        assertThat(revenue).isEqualTo(500);
    }

    @Test
    void 블랙잭이_아니면서_승리한_경우_2배의_수익을_얻는다() {
        //given
        Cards cards = new Cards(
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.TEN)
        );
        BattingMoney battingMoney = new BattingMoney(1000);

        //when
        int revenue = battingMoney.calculateRevenue(cards, WinningResult.WIN);

        //then
        assertThat(revenue).isEqualTo(1000);
    }

    @Test
    void 무승부인_경우_수익은_없다() {
        //given
        Cards cards = new Cards(
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.TEN)
        );
        BattingMoney battingMoney = new BattingMoney(1000);

        //when
        int revenue = battingMoney.calculateRevenue(cards, WinningResult.DRAW);

        //then
        assertThat(revenue).isEqualTo(0);
    }

    @Test
    void 패배한_경우_배팅금액만큼_잃는다() {
        //given
        Cards cards = new Cards(
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.TEN)
        );
        BattingMoney battingMoney = new BattingMoney(1000);

        //when
        int revenue = battingMoney.calculateRevenue(cards, WinningResult.LOSE);

        //then
        assertThat(revenue).isEqualTo(-1000);
    }
}
