package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.betting.Betting;
import model.card.Card;
import model.card.CardRank;
import model.card.CardSuit;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
import model.betting.BettingResult;
import model.winning.ParticipantWinningResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingResultTest {
    private Dealer dealer;
    private Player player;
    @BeforeEach
    void setUp() {
        player = new Player("a");
        dealer = new Dealer();
    }


    @Test
    @DisplayName("게임결과가 승이라면 베팅한 금액만큼의 수익을 얻는 테스트")
    void 게임결과가_승이라면_베팅한_금액만큼의_수익을_얻는다(){
        player.receiveCard(new Card(CardRank.EIGHT, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.FIVE, CardSuit.CLOVER));
        Players players = new Players(List.of(player));
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        Map<Player, Betting> map = new HashMap<>(Map.of(player, new Betting(10000)));
        BettingResult bettingResult = new BettingResult(map, participantWinningResult);
        Map<Player, Integer> finalResult =  bettingResult.calculatePlayerBettingResult(players, dealer);
        int expect = 10000;
        int result = finalResult.get(player);
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("게임결과가 패라면 베팅한 금액을 잃는 테스트")
    void 게임결과가_패라면_베팅한_금액을_잃는다(){
        player.receiveCard(new Card(CardRank.FIVE, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.EIGHT, CardSuit.CLOVER));
        Players players = new Players(List.of(player));
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        Map<Player, Betting> map = new HashMap<>(Map.of(player, new Betting(10000)));
        BettingResult bettingResult = new BettingResult(map, participantWinningResult);
        Map<Player, Integer> finalResult =  bettingResult.calculatePlayerBettingResult(players, dealer);
        int expect = -10000;
        int result = finalResult.get(player);
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("게임결과가 무승부라면 베팅한 손익/손실이 없도록 테스트")
    void 게임결과가_무승부라면_손익_손실이_없다(){
        player.receiveCard(new Card(CardRank.FIVE, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.FIVE, CardSuit.CLOVER));
        Players players = new Players(List.of(player));
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        Map<Player, Betting> map = new HashMap<>(Map.of(player, new Betting(10000)));
        BettingResult bettingResult = new BettingResult(map, participantWinningResult);
        Map<Player, Integer> finalResult =  bettingResult.calculatePlayerBettingResult(players, dealer);
        int expect = 0;
        int result = finalResult.get(player);
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("게임결과가 블랙잭이라면 베팅한 금액의 1.5배를 받도록 테스트")
    void 게임결과가_블랙잭일경우_수익계산(){
        player.receiveCard(new Card(CardRank.ACE, CardSuit.CLOVER));
        player.receiveCard(new Card(CardRank.KING, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.FIVE, CardSuit.CLOVER));
        Players players = new Players(List.of(player));
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        Map<Player, Betting> map = new HashMap<>(Map.of(player, new Betting(10000)));
        BettingResult bettingResult = new BettingResult(map, participantWinningResult);
        Map<Player, Integer> finalResult =  bettingResult.calculatePlayerBettingResult(players, dealer);
        int expect = 15000;
        int result = finalResult.get(player);
        assertEquals(expect, result);
    }


    @Test
    @DisplayName("보험을 걸고 딜러가 블랙잭이 아니며 게임은 이겼을 경우 테스트")
    void 보험을_걸고_딜러가_블랙잭이_아닐_경우_테스트(){
        Betting betting = new Betting(10000);
        betting.takeInsurance(4000);
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardRank.TWO, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.CLOVER));
        player.receiveCard(new Card(CardRank.KING, CardSuit.CLOVER));
        player.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));
        Players players = new Players(List.of(player));
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        Map<Player, Betting> map = new HashMap<>(Map.of(player, betting));

        BettingResult bettingResult = new BettingResult(map, participantWinningResult);

        int expect = 6000;
        Map<Player, Integer> result = bettingResult.calculatePlayerBettingResult(players, dealer);
        assertThat(result.get(player)).isEqualTo(expect);
    }

    @Test
    @DisplayName("보험을 걸고 딜러가 블랙잭이 아니며 게임도 진 경우 테스트")
    void 보험을_걸고_딜러가_블랙잭이_아니며_게임도_진_경우_테스트(){
        Betting betting = new Betting(10000);
        betting.takeInsurance(4000);
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardRank.QUEEN, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.CLOVER));
        player.receiveCard(new Card(CardRank.NINE, CardSuit.CLOVER));
        player.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));
        Players players = new Players(List.of(player));
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        Map<Player, Betting> map = new HashMap<>(Map.of(player, betting));

        BettingResult bettingResult = new BettingResult(map, participantWinningResult);

        int expect = -6000;
        Map<Player, Integer> result = bettingResult.calculatePlayerBettingResult(players, dealer);
        assertThat(result.get(player)).isEqualTo(expect);
    }

    @Test
    @DisplayName("보험을 걸고 딜러가 블랙잭이 아니며 게임은 무승부인 경우 테스트")
    void 보험을_걸고_딜러가_블랙잭이_아니며_게임은_무승부_경우_테스트(){
        Betting betting = new Betting(10000);
        betting.takeInsurance(4000);
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardRank.QUEEN, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.CLOVER));
        player.receiveCard(new Card(CardRank.QUEEN, CardSuit.CLOVER));
        player.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));
        Players players = new Players(List.of(player));
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        Map<Player, Betting> map = new HashMap<>(Map.of(player, betting));

        BettingResult bettingResult = new BettingResult(map, participantWinningResult);

        int expect = 0;
        Map<Player, Integer> result = bettingResult.calculatePlayerBettingResult(players, dealer);
        assertThat(result.get(player)).isEqualTo(expect);
    }

    @Test
    @DisplayName("플레이어가 서렌을 하고 딜러가 버스트되었을 경우")
    void 플레이어가_서렌을_하고_딜러가_버스트되었을_경우(){
        Betting betting = new Betting(10000);
        betting.surrender();
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardRank.QUEEN, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.QUEEN, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.CLOVER));
        player.receiveCard(new Card(CardRank.QUEEN, CardSuit.CLOVER));
        player.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));
        Players players = new Players(List.of(player));
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        Map<Player, Betting> map = new HashMap<>(Map.of(player, betting));

        BettingResult bettingResult = new BettingResult(map, participantWinningResult);

        int expect = -5000;
        Map<Player, Integer> result = bettingResult.calculatePlayerBettingResult(players, dealer);
        assertThat(result.get(player)).isEqualTo(expect);
    }

    @Test
    @DisplayName("플레이어가 서렌을 하고 딜러가 버스트되었을 경우 딜러의 수익계산 테스트")
    void 플레이어가_서렌을_하고_딜러가_버스트되었을_경우_딜러의_수익계산_테스트(){
        Betting betting = new Betting(10000);
        betting.surrender();
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardRank.QUEEN, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.QUEEN, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.CLOVER));
        player.receiveCard(new Card(CardRank.QUEEN, CardSuit.CLOVER));
        player.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));
        Players players = new Players(List.of(player));
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        Map<Player, Betting> map = new HashMap<>(Map.of(player, betting));

        BettingResult bettingResult = new BettingResult(map, participantWinningResult);

        int expect = 5000;
        Map<Player, Integer> playerResult = bettingResult.calculatePlayerBettingResult(players, dealer);
        int result = bettingResult.calculateDealerFinalResult(playerResult);
        assertThat(result).isEqualTo(expect);
    }
}