package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domian.Card;
import blackjack.domian.Rank;
import blackjack.domian.Suit;
import blackjack.domian.WinnerDecider;
import blackjack.domian.WinningResult;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class WinnerDeciderTest {

    @Test
    void 딜러의_점수와_같으면_무승부다() {
        //given
        List<Card> dealerCards = List.of(
                new Card(Suit.CLUB, Rank.NINE),
                new Card(Suit.CLUB, Rank.TEN)
        );

        List<Card> playerCards = List.of(
                new Card(Suit.CLUB, Rank.NINE),
                new Card(Suit.CLUB, Rank.TEN)
        );

        WinnerDecider winnerDecider = new WinnerDecider(dealerCards);

        //when
        WinningResult result = winnerDecider.decide(playerCards);

        //then
        assertThat(result).isEqualTo(WinningResult.DRAW);
    }

    @Test
    void 딜러보다_점수가_낮다면_패배이다() {
        //given
        List<Card> dealerCards = List.of(
                new Card(Suit.CLUB, Rank.NINE),
                new Card(Suit.CLUB, Rank.TEN)
        );

        List<Card> playerCards = List.of(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.CLUB, Rank.TEN)
        );

        WinnerDecider winnerDecider = new WinnerDecider(dealerCards);

        //when
        WinningResult result = winnerDecider.decide(playerCards);

        //then
        assertThat(result).isEqualTo(WinningResult.LOSE);
    }

    @Test
    void 딜러보다_점수보다_높다면_승리이다() {
        //given
        List<Card> dealerCards = List.of(
                new Card(Suit.CLUB, Rank.NINE),
                new Card(Suit.CLUB, Rank.TEN)
        );

        List<Card> playerCards = List.of(
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.CLUB, Rank.TEN)
        );

        WinnerDecider winnerDecider = new WinnerDecider(dealerCards);

        //when
        WinningResult result = winnerDecider.decide(playerCards);

        //then
        assertThat(result).isEqualTo(WinningResult.WIN);
    }

    @Test
    void 딜러가_21을_초과하면_남아있는_플레이어들의_승리이다() {
        //given
        List<Card> dealerCards = List.of(
                new Card(Suit.CLUB, Rank.NINE),
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.CLUB, Rank.THREE)
        );

        List<Card> playerCards = List.of(
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.CLUB, Rank.TEN)
        );

        WinnerDecider winnerDecider = new WinnerDecider(dealerCards);

        //when
        WinningResult result = winnerDecider.decide(playerCards);

        //then
        assertThat(result).isEqualTo(WinningResult.WIN);
    }

    @Test
    void 딜러가_블랙잭일_경우_딜러가_승리한다() {
        //given
        List<Card> dealerCards = List.of(
                new Card(Suit.CLUB, Rank.ACE),
                new Card(Suit.CLUB, Rank.TEN)
        );

        List<Card> playerCards = List.of(
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.CLUB, Rank.ONE)
        );

        WinnerDecider winnerDecider = new WinnerDecider(dealerCards);

        //when
        WinningResult result = winnerDecider.decide(playerCards);

        //then
        Assertions.assertThat(result).isEqualTo(WinningResult.LOSE);
    }

    @Test
    void 딜러와_플레이어_모두_블랙잭인_경우_무승부이다() {
        //given
        List<Card> dealerCards = List.of(
                new Card(Suit.CLUB, Rank.ACE),
                new Card(Suit.CLUB, Rank.TEN)
        );

        List<Card> playerCards = List.of(
                new Card(Suit.CLUB, Rank.ACE),
                new Card(Suit.CLUB, Rank.TEN)
        );

        WinnerDecider winnerDecider = new WinnerDecider(dealerCards);

        //when
        WinningResult result = winnerDecider.decide(playerCards);

        //then
        Assertions.assertThat(result).isEqualTo(WinningResult.DRAW);
    }
}
