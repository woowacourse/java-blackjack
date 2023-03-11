package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Number;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JudgeTest {

    @Test
    @DisplayName("플레이어가 21을 초과하면 패배한다")
    void playerOver21() {
        //given
        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(Suit.CLOVER, Number.FIVE), new Card(Suit.SPADE, Number.EIGHT))));
        Player player = new Player("pobi", new Cards(
            new ArrayList<>(
                List.of(new Card(Suit.CLOVER, Number.J), new Card(Suit.SPADE, Number.Q)))));
        player.hit(new Card(Suit.SPADE, Number.THREE));
        //when
        GameResult result = Judge.of(dealer, player);
        //then
        Assertions.assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 플레이어가 21보다 작고 딜러가 클 때 플레이어가 패배한다")
    void dealerGreaterThanPlayerUnder21() {
        //given
        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(Suit.CLOVER, Number.J), new Card(Suit.SPADE, Number.Q))));
        Player player = new Player("pobi", new Cards(
            new ArrayList<>(
                List.of(new Card(Suit.CLOVER, Number.THREE), new Card(Suit.SPADE, Number.TWO)))));
        //when
        GameResult result = Judge.of(dealer, player);
        //then
        Assertions.assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 21을 초과하고 플레이어가 21보다 작으면 플레이어가 승리한다")
    void dealerOver21() {
        //given
        Dealer dealer = new Dealer(new Cards(
            new ArrayList<>(
                List.of(new Card(Suit.CLOVER, Number.J), new Card(Suit.SPADE, Number.Q)))));
        dealer.hit(new Card(Suit.SPADE, Number.THREE));

        Player player = new Player("pobi", new Cards(
            List.of(new Card(Suit.CLOVER, Number.FIVE), new Card(Suit.SPADE, Number.EIGHT))));
        //when
        GameResult result = Judge.of(dealer, player);
        //then
        Assertions.assertThat(result).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어가 21보다 작고 플레이어가 클 때 플레이어가 승리한다")
    void playerGreaterThanDealerUnder21() {
        //given
        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(Suit.CLOVER, Number.SIX), new Card(Suit.SPADE, Number.Q))));
        Player player = new Player("pobi", new Cards(
            new ArrayList<>(
                List.of(new Card(Suit.CLOVER, Number.J), new Card(Suit.SPADE, Number.Q)))));
        //when
        GameResult result = Judge.of(dealer, player);
        //then
        Assertions.assertThat(result).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어가 21보다 작고 점수가 같으면 무승부이다")
    void draw() {
        //given
        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(Suit.CLOVER, Number.SIX), new Card(Suit.SPADE, Number.Q))));
        Player player = new Player("pobi", new Cards(
            new ArrayList<>(
                List.of(new Card(Suit.CLOVER, Number.SIX), new Card(Suit.SPADE, Number.Q)))));
        //when
        GameResult result = Judge.of(dealer, player);
        //then
        Assertions.assertThat(result).isEqualTo(GameResult.DRAW);
    }
}
