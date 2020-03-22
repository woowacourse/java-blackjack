package domain.gamer;

import domain.PlayersResult;
import domain.card.Card;
import domain.card.PlayingCards;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class DealerWalletsTest {

    @Test
    @DisplayName("딜러가 플레이어를 이겼을때 수익이 계산된다.")
    void dealerWinProfitTest() {
        PlayingCards playingCards = new PlayingCards(Arrays.asList(new Card(Symbol.FOUR, Type.HEART)));
        PlayingCards dealerPlayingCards = new PlayingCards(Arrays.asList(new Card(Symbol.FIVE, Type.DIAMOND)));
        Players players = new Players(Arrays.asList(new Player("test", 1000, playingCards)));
        Dealer dealer = new Dealer(dealerPlayingCards);
        PlayersResult playersResult = new PlayersResult(players, dealer);
        PlayerWallets playerWallets = new PlayerWallets(playersResult);
        DealerWallet dealerWallet = new DealerWallet(dealer,playerWallets);
        assertThat(dealerWallet.getMoney()).isEqualTo(1000);
    }

    @Test
    @DisplayName("딜러가 플레이어에게 졌을때 수익이 계산된다.")
    void dealerLoseProfitTest() {
        PlayingCards playingCards = new PlayingCards(Arrays.asList(new Card(Symbol.SIX, Type.HEART)));
        PlayingCards dealerPlayingCards = new PlayingCards(Arrays.asList(new Card(Symbol.FIVE, Type.DIAMOND)));
        Players players = new Players(Arrays.asList(new Player("test", 1000, playingCards)));
        Dealer dealer = new Dealer(dealerPlayingCards);
        PlayersResult playersResult = new PlayersResult(players, dealer);
        PlayerWallets playerWallets = new PlayerWallets(playersResult);
        DealerWallet dealerWallet = new DealerWallet(dealer,playerWallets);
        assertThat(dealerWallet.getMoney()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("딜러와 플레이어가 비겼을때 수익이 계산된다.")
    void dealerDrawProfitTest() {
        PlayingCards playingCards = new PlayingCards(Arrays.asList(new Card(Symbol.FIVE, Type.HEART)));
        PlayingCards dealerPlayingCards = new PlayingCards(Arrays.asList(new Card(Symbol.FIVE, Type.DIAMOND)));
        Players players = new Players(Arrays.asList(new Player("test", 0, playingCards)));
        Dealer dealer = new Dealer(dealerPlayingCards);
        PlayersResult playersResult = new PlayersResult(players, dealer);
        PlayerWallets playerWallets = new PlayerWallets(playersResult);
        DealerWallet dealerWallet = new DealerWallet(dealer,playerWallets);
        assertThat(dealerWallet.getMoney()).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러가 플레이어에게 블랙잭 패배를 했을때 수익이 계산된다.")
    void dealerBlackJackLoseProfitTest() {
        PlayingCards playingCards = new PlayingCards(Arrays.asList(new Card(Symbol.TEN, Type.DIAMOND)
                , new Card(Symbol.ACE, Type.DIAMOND)));
        PlayingCards dealerPlayingCards = new PlayingCards(Arrays.asList(new Card(Symbol.NINE, Type.SPADE)));

        Players players = new Players(Arrays.asList(new Player("test", 1000, playingCards)));
        Dealer dealer = new Dealer(dealerPlayingCards);
        PlayersResult playersResult = new PlayersResult(players, dealer);
        PlayerWallets playerWallets = new PlayerWallets(playersResult);
        DealerWallet dealerWallet = new DealerWallet(dealer,playerWallets);
        assertThat(dealerWallet.getMoney()).isEqualTo(-1500);
    }

    @Test
    @DisplayName("딜러가 플레이어를 둘다 버스트 됐을 때 수익이 계산된다.")
    void dealerAndPlayerBustProfitTest() {
        PlayingCards playingCards = new PlayingCards(Arrays.asList(new Card(Symbol.TEN, Type.HEART)
                , new Card(Symbol.TEN, Type.DIAMOND), new Card(Symbol.TEN, Type.SPADE)));

        PlayingCards dealerPlayingCards = new PlayingCards(Arrays.asList(new Card(Symbol.NINE, Type.DIAMOND)
                , new Card(Symbol.NINE, Type.CLOVER), new Card(Symbol.NINE, Type.SPADE)));

        Players players = new Players(Arrays.asList(new Player("test", 1000, playingCards)));
        Dealer dealer = new Dealer(dealerPlayingCards);
        PlayersResult playersResult = new PlayersResult(players, dealer);
        PlayerWallets playerWallets = new PlayerWallets(playersResult);
        DealerWallet dealerWallet = new DealerWallet(dealer,playerWallets);
        assertThat(dealerWallet.getMoney()).isEqualTo(1000);
    }
}
