package blackjack.domain.betting;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BettingYieldCalculatorTest {
    private static Dealer dealerBlackjack;
    private static Player playerBlackjack;
    private static Dealer dealer;
    private static Players players;
    private static Player player;

    @BeforeAll
    static void setBlackjack() {
        dealerBlackjack = new Dealer();
        dealerBlackjack.receiveCard(new Card(CardShape.CLOVER, CardNumber.ACE));
        dealerBlackjack.receiveCard(new Card(CardShape.CLOVER, CardNumber.KING));


        playerBlackjack = new Player("IO");
        playerBlackjack.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));
        playerBlackjack.receiveCard(new Card(CardShape.HEART, CardNumber.KING));
    }

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        dealer.receiveCard(new Card(CardShape.HEART, CardNumber.JACK));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.EIGHT));

        player = new Player("pobi");
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.FOUR));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.SIX));
        players = new Players(List.of(player));
    }

    @Test
    @DisplayName("베팅 수익률 확인: 버스터 없이 플레이어가 지는 경우")
    void bettingYieldCalculator1() {
        BettingYieldCalculator bettingYieldCalculator = new BettingYieldCalculator(dealer, players.getPlayers());

        assertThat(bettingYieldCalculator.getPlayersYields().get(player)).isEqualTo(-1);
    }

    @Test
    @DisplayName("베팅 수익률 확인: 버스터 없이 비기는 경우")
    void bettingYieldCalculator2() {
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.EIGHT));

        BettingYieldCalculator bettingYieldCalculator = new BettingYieldCalculator(dealer, players.getPlayers());

        assertThat(bettingYieldCalculator.getPlayersYields().get(player)).isEqualTo(0);
    }

    @Test
    @DisplayName("베팅 수익률 확인: 버스터 없이 딜러가 지는 경우")
    void bettingYieldCalculator3() {
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.EIGHT));
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.TWO));

        BettingYieldCalculator bettingYieldCalculator = new BettingYieldCalculator(dealer, players.getPlayers());

        assertThat(bettingYieldCalculator.getPlayersYields().get(player)).isEqualTo(1);
    }

    @Test
    @DisplayName("베팅 수익률 확인: 플레이어만 버스터인 경우")
    void bettingYieldCalculator4() {
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.EIGHT));
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.KING));

        BettingYieldCalculator bettingYieldCalculator = new BettingYieldCalculator(dealer, players.getPlayers());

        assertThat(bettingYieldCalculator.getPlayersYields().get(player)).isEqualTo(-1);
    }

    @Test
    @DisplayName("베팅 수익률 확인: 딜러만 버스터인 경우")
    void bettingYieldCalculator5() {
        dealer.receiveCard(new Card(CardShape.CLOVER, CardNumber.SEVEN));

        BettingYieldCalculator bettingYieldCalculator = new BettingYieldCalculator(dealer, players.getPlayers());

        assertThat(bettingYieldCalculator.getPlayersYields().get(player)).isEqualTo(1);
    }

    @Test
    @DisplayName("베팅 수익률 확인: 둘 다 버스터인 경우")
    void bettingYieldCalculator6() {
        dealer.receiveCard(new Card(CardShape.CLOVER, CardNumber.SEVEN));
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.EIGHT));
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.KING));

        BettingYieldCalculator bettingYieldCalculator = new BettingYieldCalculator(dealer, players.getPlayers());

        assertThat(bettingYieldCalculator.getPlayersYields().get(player)).isEqualTo(-1);
    }

    @Test
    @DisplayName("베팅 수익률 확인 : 플레이어 여러명일 때")
    void multiPlayer() {
        Player player2 = new Player("jena");
        player2.receiveCard(new Card(CardShape.CLOVER, CardNumber.ACE));
        player2.receiveCard(new Card(CardShape.DIAMOND, CardNumber.SEVEN));
        Player player3 = new Player("io");
        player3.receiveCard(new Card(CardShape.CLOVER, CardNumber.JACK));
        player3.receiveCard(new Card(CardShape.DIAMOND, CardNumber.KING));

        players = new Players(List.of(player, player2, player3));
        BettingYieldCalculator bettingYieldCalculator = new BettingYieldCalculator(dealer, players.getPlayers());

        assertThat(bettingYieldCalculator.getPlayersYields().get(player)).isEqualTo(-1);
        assertThat(bettingYieldCalculator.getPlayersYields().get(player2)).isEqualTo(0);
        assertThat(bettingYieldCalculator.getPlayersYields().get(player3)).isEqualTo(1);
    }

    @Test
    @DisplayName("베팅 수익률률 확인 : 딜러만 블랙잭일 떄")
    void whenOnlyDealerBlackjackTest() {
        BettingYieldCalculator bettingYieldCalculator = new BettingYieldCalculator(dealerBlackjack, players.getPlayers());

        assertThat(bettingYieldCalculator.getPlayersYields().get(player)).isEqualTo(-1);
    }

    @Test
    @DisplayName("베팅 수익률률 확인 : 모두 블랙잭일 떄")
    void whenBothBlackjackTest() {
        BettingYieldCalculator bettingYieldCalculator = new BettingYieldCalculator(dealerBlackjack, List.of(playerBlackjack));

        assertThat(bettingYieldCalculator.getPlayersYields().get(playerBlackjack)).isEqualTo(0);
    }

    @Test
    @DisplayName("베팅 수익률률 확인 : 플레이어만 블랙잭일 떄")
    void whenOnlyPlayerBlackjackTest() {
        BettingYieldCalculator bettingYieldCalculator = new BettingYieldCalculator(dealer, List.of(playerBlackjack));

        assertThat(bettingYieldCalculator.getPlayersYields().get(playerBlackjack)).isEqualTo(1.5);
    }

}