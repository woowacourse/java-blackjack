package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
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
            List.of(Card.of(Suit.CLOVER, Denomination.FIVE),
                Card.of(Suit.SPADE, Denomination.EIGHT))));
        Cards cards = new Cards(
            new ArrayList<>(
                List.of(Card.of(Suit.CLOVER, Denomination.J),
                    Card.of(Suit.SPADE, Denomination.Q))));
        Player player = new Player("pobi");
        player.initCards(cards);
        //when
        player.hit(Card.of(Suit.SPADE, Denomination.THREE));
        GameState result = Judge.gameResult(dealer, player);
        //then
        Assertions.assertThat(result).isEqualTo(GameState.LOSE);
    }

    @Test
    @DisplayName("딜러와 플레이어가 21보다 작고 딜러가 클 때 플레이어가 패배한다")
    void dealerGreaterThanPlayerUnder21() {
        //given
        Dealer dealer = new Dealer(new Cards(
            List.of(Card.of(Suit.CLOVER, Denomination.J), Card.of(Suit.SPADE, Denomination.Q))));
        Cards cards = new Cards(
            new ArrayList<>(
                List.of(Card.of(Suit.CLOVER, Denomination.THREE),
                    Card.of(Suit.SPADE, Denomination.TWO))));
        Player player = new Player("pobi");
        player.initCards(cards);
        //when
        GameState result = Judge.gameResult(dealer, player);
        //then
        Assertions.assertThat(result).isEqualTo(GameState.LOSE);
    }

    @Test
    @DisplayName("딜러가 21을 초과하고 플레이어가 21보다 작으면 플레이어가 승리한다")
    void dealerOver21() {
        //given
        Dealer dealer = new Dealer(new Cards(
            new ArrayList<>(
                List.of(Card.of(Suit.CLOVER, Denomination.J),
                    Card.of(Suit.SPADE, Denomination.Q)))));
        dealer.hit(Card.of(Suit.SPADE, Denomination.THREE));

        Cards cards = new Cards(
            List.of(Card.of(Suit.CLOVER, Denomination.FIVE),
                Card.of(Suit.SPADE, Denomination.EIGHT)));
        Player player = new Player("pobi");
        player.initCards(cards);
        //when
        GameState result = Judge.gameResult(dealer, player);
        //then
        Assertions.assertThat(result).isEqualTo(GameState.WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어가 21보다 작고 플레이어가 클 때 플레이어가 승리한다")
    void playerGreaterThanDealerUnder21() {
        //given
        Dealer dealer = new Dealer(new Cards(
            List.of(Card.of(Suit.CLOVER, Denomination.SIX),
                Card.of(Suit.SPADE, Denomination.Q))));
        Cards cards = new Cards(
            new ArrayList<>(
                List.of(Card.of(Suit.CLOVER, Denomination.J),
                    Card.of(Suit.SPADE, Denomination.Q))));
        Player player = new Player("pobi");
        player.initCards(cards);
        //when
        GameState result = Judge.gameResult(dealer, player);
        //then
        Assertions.assertThat(result).isEqualTo(GameState.WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어가 21보다 작고 점수가 같으면 무승부이다")
    void draw() {
        //given
        Dealer dealer = new Dealer(new Cards(
            List.of(Card.of(Suit.CLOVER, Denomination.SIX),
                Card.of(Suit.SPADE, Denomination.Q))));
        Cards cards = new Cards(
            new ArrayList<>(
                List.of(Card.of(Suit.CLOVER, Denomination.SIX),
                    Card.of(Suit.SPADE, Denomination.Q))));
        Player player = new Player("pobi");
        player.initCards(cards);
        //when
        GameState result = Judge.gameResult(dealer, player);
        //then
        Assertions.assertThat(result).isEqualTo(GameState.DRAW);
    }
}
