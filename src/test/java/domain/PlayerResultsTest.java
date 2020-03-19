package domain;

import domain.card.Card;
import domain.card.PlayingCards;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerResultsTest {

    @Test
    @DisplayName("딜러가 플레어어 에게 패 할 경우의 딜러 이익이 플레이어의 배팅 금액 만큼 감소한다.")
    void dealerProfitWhenDealerLosePlayer() {
        Card card1 = new Card(Symbol.QUEEN, Type.CLOVER);
        Card card2 = new Card(Symbol.FOUR, Type.DIAMOND);
        Card card3 = new Card(Symbol.FOUR, Type.DIAMOND);
        Card card4 = new Card(Symbol.FOUR, Type.DIAMOND);

        PlayingCards playerPlayingCards = new PlayingCards(Arrays.asList(card1,card2));
        PlayingCards dealerPlayingCards = new PlayingCards(Arrays.asList(card3,card4));
        Player player1 = new Player("player1", 10000,playerPlayingCards);
        Dealer dealer = new Dealer(dealerPlayingCards);
        Players players = new Players(Arrays.asList(player1));
        PlayersResult playersResult = new PlayersResult(players, dealer);
        assertThat(playersResult.dealerProfit()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("딜러가 플레어어 에게 이길 경우의 딜러 이익이 플레이어 배팅 금액만큼 증가한다")
    void dealerProfitWhenDealerWinPlayer() {
        Card card1 = new Card(Symbol.FOUR, Type.CLOVER);
        Card card2 = new Card(Symbol.FOUR, Type.HEART);
        Card card3 = new Card(Symbol.FIVE, Type.DIAMOND);
        Card card4 = new Card(Symbol.FOUR, Type.DIAMOND);

        PlayingCards playerPlayingCards = new PlayingCards(Arrays.asList(card1,card2));
        PlayingCards dealerPlayingCards = new PlayingCards(Arrays.asList(card3,card4));
        Player player1 = new Player("player1", 10000,playerPlayingCards);
        Dealer dealer = new Dealer(dealerPlayingCards);
        Players players = new Players(Arrays.asList(player1));
        PlayersResult playersResult = new PlayersResult(players, dealer);
        assertThat(playersResult.dealerProfit()).isEqualTo(10000);
    }

    @Test
    @DisplayName("딜러와 플레이어가 비길 경우의 딜러 이익이 0이 된다.")
    void dealerProfitWhenDealerDrawPlayer() {
        Card card1 = new Card(Symbol.FOUR, Type.CLOVER);
        Card card2 = new Card(Symbol.FOUR, Type.SPADE);
        Card card3 = new Card(Symbol.FOUR, Type.HEART);
        Card card4 = new Card(Symbol.FOUR, Type.DIAMOND);

        PlayingCards playerPlayingCards = new PlayingCards(Arrays.asList(card1,card2));
        PlayingCards dealerPlayingCards = new PlayingCards(Arrays.asList(card3,card4));
        Player player1 = new Player("player1", 10000,playerPlayingCards);
        Dealer dealer = new Dealer(dealerPlayingCards);
        Players players = new Players(Arrays.asList(player1));
        PlayersResult playersResult = new PlayersResult(players, dealer);
        assertThat(playersResult.dealerProfit()).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러가 플레이어에게 블랙잭 패 할 경우의 딜러 이익이 플레이어 배팅금액의 1.5배 만큼 감소한다.")
    void dealerProfitWhenDealerBlackJackLosePlayer() {
        Card card1 = new Card(Symbol.TEN, Type.CLOVER);
        Card card2 = new Card(Symbol.ACE, Type.SPADE);
        Card card3 = new Card(Symbol.TWO, Type.HEART);
        Card card4 = new Card(Symbol.FOUR, Type.DIAMOND);

        PlayingCards playerPlayingCards = new PlayingCards(Arrays.asList(card1,card2));
        PlayingCards dealerPlayingCards = new PlayingCards(Arrays.asList(card3,card4));
        Player player1 = new Player("player1", 10000,playerPlayingCards);
        Dealer dealer = new Dealer(dealerPlayingCards);
        Players players = new Players(Arrays.asList(player1));
        PlayersResult playersResult = new PlayersResult(players, dealer);
        assertThat(playersResult.dealerProfit()).isEqualTo(-15000);
    }

    @Test
    @DisplayName("플레이어가 버스트해서 딜러가 승리 할 경우의 딜러 이익이 플레이어 배팅금액의 1.5배 만큼 감소한다.")
    void dealerProfitWhenDealerWinPlayerBust() {
        Card card1 = new Card(Symbol.TEN, Type.CLOVER);
        Card card2 = new Card(Symbol.TEN, Type.SPADE);
        Card card3 = new Card(Symbol.NINE, Type.SPADE);
        Card card4 = new Card(Symbol.TWO, Type.HEART);
        Card card5 = new Card(Symbol.FOUR, Type.DIAMOND);

        PlayingCards playerPlayingCards = new PlayingCards(Arrays.asList(card1,card2,card3));
        PlayingCards dealerPlayingCards = new PlayingCards(Arrays.asList(card4,card5));
        Player player1 = new Player("player1", 10000,playerPlayingCards);
        Dealer dealer = new Dealer(dealerPlayingCards);
        Players players = new Players(Arrays.asList(player1));
        PlayersResult playersResult = new PlayersResult(players, dealer);
        assertThat(playersResult.dealerProfit()).isEqualTo(10000);
    }
}
